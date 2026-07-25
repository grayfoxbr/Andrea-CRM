package com.angels.andrea_resource_api.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Wrapper fino sobre {@link ReactiveRedisTemplate} para o padrão
 * "cache-aside" reativo: tenta ler do Redis; em caso de miss, executa o
 * {@code loader} (a consulta real no banco via R2DBC), grava o resultado no
 * cache e o devolve.
 *
 * Importante para o stack reativo: aqui a decisão cache-hit/cache-miss e a
 * gravação do valor acontecem dentro do próprio pipeline reativo (via
 * switchIfEmpty/flatMap), então não há reexecução indevida da fonte como
 * aconteceria usando {@code @Cacheable} direto em métodos que retornam
 * {@code Mono}/{@code Flux}.
 *
 * Falhas de conexão com o Redis não derrubam a aplicação: em caso de erro na
 * leitura/escrita do cache, cai-se de volta para o {@code loader} (fail-open).
 */
@Component
public class ReactiveCacheService {

    private static final Logger log = LoggerFactory.getLogger(ReactiveCacheService.class);

    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    public ReactiveCacheService(ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    public <T> Mono<T> cacheable(String key, Class<T> type, Duration ttl, Supplier<Mono<T>> loader) {
        return redisTemplate.opsForValue().get(key)
                .cast(type)
                .doOnNext(hit -> log.debug("cache hit: {}", key))
                .switchIfEmpty(Mono.defer(() -> loader.get()
                        .flatMap(value -> redisTemplate.opsForValue().set(key, value, ttl)
                                .doOnError(ex -> log.warn("falha ao gravar cache {}: {}", key, ex.getMessage()))
                                .onErrorReturn(false)
                                .thenReturn(value))))
                .onErrorResume(ex -> {
                    log.warn("falha ao ler cache {}, seguindo sem cache: {}", key, ex.getMessage());
                    return loader.get();
                });
    }

    public Mono<Boolean> evict(String key) {
        return redisTemplate.opsForValue().delete(key)
                .onErrorResume(ex -> {
                    log.warn("falha ao invalidar cache {}: {}", key, ex.getMessage());
                    return Mono.just(false);
                });
    }

    public Mono<Long> evictByPrefix(String prefix) {
        return redisTemplate.keys(prefix + "*")
                .collectList()
                .flatMap(keys -> keys.isEmpty()
                        ? Mono.just(0L)
                        : redisTemplate.delete(Flux.fromIterable(keys)))
                .onErrorResume(ex -> {
                    log.warn("falha ao invalidar cache por prefixo {}: {}", prefix, ex.getMessage());
                    return Mono.just(0L);
                });
    }
}
