package com.example.appauthbase.config

/**
 * Guarda em memória o access_token atual, para que o [AuthInterceptor]
 * possa anexá-lo como "Authorization: Bearer <token>" em chamadas às APIs
 * protegidas (Companies, Leads, Clients, etc.), sem precisar passar o
 * token manualmente em cada request.
 *
 * IMPORTANTE: isto precisa ser atualizado sempre que o token mudar.
 * Ponto de integração sugerido: dentro de OAuthDataSource, no método
 * save(), adicione a linha:
 *
 *     TokenHolder.token = state.accessToken
 *
 * Isso garante que o holder fica sincronizado tanto após login quanto
 * após logout (accessToken vira null) e após refresh de token.
 */
object TokenHolder {
    @Volatile
    var token: String? = null
}