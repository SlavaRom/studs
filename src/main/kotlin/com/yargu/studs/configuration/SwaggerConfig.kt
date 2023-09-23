package com.empiritek.fdab.websitesbackend

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@ConditionalOnProperty(prefix = "test", name = ["env"], matchIfMissing = true, havingValue = "false")
@Configuration
@EnableWebMvc
class SwaggerConfig {

    @Bean
    fun springShopOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info().title("Vsble API")
                    .description("Vsble application")
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}
