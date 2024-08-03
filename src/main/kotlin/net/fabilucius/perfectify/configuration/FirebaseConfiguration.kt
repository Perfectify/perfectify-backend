package net.fabilucius.perfectify.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.*

@Configuration
class FirebaseConfiguration {

    @Bean
    fun firebaseApp(): FirebaseApp {
        val serviceAccountJson = ClassPathResource("service_accounts.json")
        val inputStream = if (serviceAccountJson.exists()) {
            serviceAccountJson.inputStream
        } else {
            val decoded = Base64.getDecoder().decode(System.getenv("SERVICE_ACCOUNT_BASE64"))
            ByteArrayInputStream(decoded)
        }
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(inputStream))
            .build()
        return FirebaseApp.initializeApp(options)
    }

}