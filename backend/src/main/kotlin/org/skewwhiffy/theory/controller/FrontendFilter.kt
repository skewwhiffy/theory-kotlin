package org.skewwhiffy.theory.org.skewwhiffy.theory.controller

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component

@Component
class FrontendFilter(
    private val resourceLoader: ResourceLoader
) : Filter {
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse?,
        chain: FilterChain
    ) {
        chain.doFilter(request, response)
//        val req = request as HttpServletRequest
//        val requestURI = req.requestURI
//        if (requestURI.startsWith("/api")) {
//            chain.doFilter(request, response)
//            return
//        }
//        val resourcePath = listOf("public", requestURI).joinToString("/") { it.trim('/') }
//        val resource = resourceLoader.getResource("classpath:$resourcePath")
//        if (resource.exists() && resource.isFile) {
//            chain.doFilter(request, response)
//        } else {
//            request.getRequestDispatcher("/").forward(request, response)
//        }
    }
}