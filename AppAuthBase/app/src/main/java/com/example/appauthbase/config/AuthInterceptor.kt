package com.example.appauthbase.config

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Anexa automaticamente o header "Authorization: Bearer <token>" em toda
 * requisição, usando o token atual guardado em [TokenHolder].
 *
 * Se não houver token (usuário deslogado), a requisição segue sem o
 * header — o backend deve retornar 401/403 nesse caso, o que é o
 * comportamento esperado para endpoints protegidos.
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = TokenHolder.token
        if (token.isNullOrBlank()) {
            return chain.proceed(original)
        }

        val authenticated = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(authenticated)
    }
}