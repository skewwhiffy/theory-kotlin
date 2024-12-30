package org.skewwhiffy.theory.org.skewwhiffy.theory.configuration

import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {
    companion object {
        init {
            ModelResolver.enumsAsRef = true
        }
    }

    @Bean
    fun openAPI(): OpenAPI =
        Info()
            .title("Music Theory")
            .description("Music Theory API")
            .version("v0.0.1")
            .let { OpenAPI().info(it) }
}