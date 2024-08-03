package net.fabilucius.perfectify.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        with(httpSecurity) {
            csrf {
                it.disable()
            }
            authorizeHttpRequests {
                it.anyRequest().authenticated()
            }
            oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }
        }
        return httpSecurity.build()
    }

}