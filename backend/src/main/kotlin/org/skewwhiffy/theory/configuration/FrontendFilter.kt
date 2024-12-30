package org.skewwhiffy.theory.org.skewwhiffy.theory.configuration

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component

private val allowedResources = setOf(
    "swagger-ui",
    "api-docs"
)

@Component
class FrontendFilter(
    private val resourceLoader: ResourceLoader
) : Filter {
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse?,
        chain: FilterChain
    ) {
        val req = request as HttpServletRequest
        val requestURI = req.requestURI.trim('/')
        val resourcePath = listOf("public", requestURI).joinToString("/")
        val resource = resourceLoader.getResource("classpath:$resourcePath")
        val isApiRequest = requestURI.startsWith("api")
        val isAllowedResource = allowedResources.any { requestURI.contains(it) }
        val staticFileExists = resource.exists() && resource.isFile
        if (isApiRequest || isAllowedResource || staticFileExists) {
            chain.doFilter(request, response)
        } else {
            request.getRequestDispatcher("/").forward(request, response)
        }
    }
}