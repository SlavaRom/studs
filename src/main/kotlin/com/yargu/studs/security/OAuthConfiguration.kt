package com.yargu.studs.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Order(1)
@Configuration
class OAuthConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/student/**")
        web.ignoring().antMatchers("/s3/**")
        web.ignoring().antMatchers("/v2/api-docs")
        web.ignoring().antMatchers("/swagger-ui/**")
        web.ignoring().antMatchers("/swagger-resources/**")
        web.ignoring().antMatchers("/v3/api-docs/**")
        web.ignoring().antMatchers("/swagger-ui.html")
        web.ignoring().antMatchers("/api/swagger-ui.html")
        web.ignoring().antMatchers("/api-docs/**")

        web.ignoring().antMatchers("/actuator/**")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}