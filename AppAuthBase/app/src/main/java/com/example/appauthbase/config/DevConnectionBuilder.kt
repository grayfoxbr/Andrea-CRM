// AppAuthBase/app/src/main/java/com/example/appauthbase/config/DevConnectionBuilder.kt
package com.example.appauthbase.config

import net.openid.appauth.connectivity.ConnectionBuilder
import java.net.HttpURLConnection
import java.net.URL

/**
 * ATENÇÃO: permite conexões HTTP (sem TLS) para o AppAuth.
 * Uso exclusivo em desenvolvimento local (servidor rodando em http://localhost).
 * NÃO utilizar em builds de produção — lá o servidor deve usar HTTPS
 * e o DefaultConnectionBuilder do AppAuth deve ser restaurado.
 */
object DevConnectionBuilder : ConnectionBuilder {
    override fun openConnection(uri: android.net.Uri): HttpURLConnection {
        val url = URL(uri.toString())
        return url.openConnection() as HttpURLConnection
    }
}