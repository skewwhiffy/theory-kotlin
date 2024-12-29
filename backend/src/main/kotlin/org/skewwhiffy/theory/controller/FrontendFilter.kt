package org.skewwhiffy.theory.org.skewwhiffy.theory.controller

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
        val requestURI = req.requestURI
        val resourcePath = listOf("public", requestURI).joinToString("/") { it.trim('/') }
        val resource = resourceLoader.getResource("classpath:$resourcePath")
        if (allowedResources.any { requestURI.contains(it) }) {
            chain.doFilter(request, response)
            return
        }
        if (allowedResources.contains(requestURI.trim('/')) || (resource.exists() && resource.isFile)) {
            chain.doFilter(request, response)
        } else {
            request.getRequestDispatcher("/").forward(request, response)
        }
    }
}