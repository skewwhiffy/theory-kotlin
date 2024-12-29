package org.skewwhiffy.theory.org.skewwhiffy.theory.controller

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class FrontendFilter : Filter {
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse?,
        chain: FilterChain
    ) {
        val req = request as HttpServletRequest
        val requestURI = req.requestURI

        if (requestURI.startsWith("/api")) {
            chain.doFilter(request, response)
            return
        }

        request.getRequestDispatcher("/").forward(request, response)
    }
}