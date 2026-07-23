package com.example.appauthbase.data.datasource

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.appauthbase.config.NetworkConfig
import com.example.appauthbase.config.DevConnectionBuilder
import net.openid.appauth.*

class OAuthDataSource(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "auth",
            Context.MODE_PRIVATE
        )

    private var state = readState()

    private val service =
        AuthorizationService(
            context,
            AppAuthConfiguration.Builder()
                .setConnectionBuilder(DevConnectionBuilder) // apenas para dev com HTTP para Desenvolvimento APENAS
                .build()
        )

    private val config =
        AuthorizationServiceConfiguration(
            Uri.parse(NetworkConfig.AUTHORIZE_URL),
            Uri.parse(NetworkConfig.TOKEN_URL)
        )

    fun buildIntent(): Intent {

        val request =
            AuthorizationRequest
                .Builder(
                    config,
                    NetworkConfig.CLIENT_ID,
                    ResponseTypeValues.CODE,
                    Uri.parse(NetworkConfig.REDIRECT_URI)
                )
                .setScope(NetworkConfig.SCOPES)
                // PKCE é obrigatório pois o client usa requireProofKey(true)
                .build()

        return service.getAuthorizationRequestIntent(request)
    }

    fun authorized() = state.isAuthorized

    fun token() = state.accessToken

    fun clear() {
        state = AuthState()
        save()
    }

    fun handleResult(
        intent: Intent,
        callback: (Boolean, Exception?) -> Unit
    ) {
        val response = AuthorizationResponse.fromIntent(intent)
        val exception = AuthorizationException.fromIntent(intent)

        android.util.Log.e("OAuthDataSource", "authResponse=$response, authException=$exception")

        state.update(response, exception)
        save()

        if (response == null) {
            callback(false, exception)
            return
        }

        service.performTokenRequest(
            response.createTokenExchangeRequest()
        ) { token, ex ->
            android.util.Log.e("OAuthDataSource", "tokenResponse=$token, tokenException=$ex")
            state.update(token, ex)
            save()
            callback(token != null, ex)
        }
    }

    private fun save() {
        prefs.edit()
            .putString("state", state.jsonSerializeString())
            .apply()
    }

    private fun readState(): AuthState {
        return prefs
            .getString("state", null)
            ?.let { AuthState.jsonDeserialize(it) }
            ?: AuthState()
    }
}