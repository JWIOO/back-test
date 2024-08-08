package com.aurum.educerti.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize.anyRequest().permitAll()  // 모든 요청에 대해 인증 비활성화
            }
            .csrf { csrf ->
                csrf.disable()  // CSRF 비활성화
            }
            .headers { headers ->
                headers.frameOptions { frameOptions ->
                    frameOptions.sameOrigin()  // 동일 출처에서만 iframe 허용
                }
            }

        return http.build()
    }
}
