package com.angels.andrea_resource_api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * Template reativo genérico usado pelo {@link com.angels.andrea_resource_api.cache.ReactiveCacheService}
     * para cachear qualquer uma das entidades do módulo (Lead, Client, LeadTask, ClientTask).
     *
     * Usa Jackson com "default typing" para gravar o tipo concreto junto do JSON, permitindo
     * desserializar de volta para a classe correta sem precisar de um template por entidade.
     */
    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL);

        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        RedisSerializationContext<String, Object> context = RedisSerializationContext
                .<String, Object>newSerializationContext(new StringRedisSerializer())
                .value(valueSerializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
