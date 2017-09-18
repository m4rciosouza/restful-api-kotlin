package com.kazale.pontointeligente.api.pontointeligenteapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
        val funcionarioDetailsService: FuncionarioDetailsService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()?.
                anyRequest()?.
                authenticated()?.and()?.
                httpBasic()?.and()?.
                sessionManagement()?.
                sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.
                csrf()?.disable()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(funcionarioDetailsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

}
