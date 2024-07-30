package com.aurum.educerti.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurityConfig(private val userDetailsService: UserDetailsService) {

    @Bean
    fun configure(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(AntPathRequestMatcher("/static/**"))
        }
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            // .authorizeHttpRequests { auth ->
            //     auth
            //         .requestMatchers(
            //             AntPathRequestMatcher("/login"),
            //             AntPathRequestMatcher("/signup"),
            //             AntPathRequestMatcher("/user"),
            //             AntPathRequestMatcher("/api/**")
            //         ).permitAll()
            //         .anyRequest().authenticated()
            // }
            // .formLogin { formLogin ->
            //     formLogin
            //         .loginPage("/login")
            //         .defaultSuccessUrl("/articles")
            // }
            // .logout { logout ->
            //     logout
            //         .logoutSuccessUrl("/login")
            //         .invalidateHttpSession(true)
            // }
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(
        bCryptPasswordEncoder: BCryptPasswordEncoder
    ): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(bCryptPasswordEncoder)
        return AuthenticationManager { authProvider.authenticate(it) }
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
