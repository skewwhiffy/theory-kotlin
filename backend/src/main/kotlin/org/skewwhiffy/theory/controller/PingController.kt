package org.skewwhiffy.theory.org.skewwhiffy.theory.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ping")
class PingController {
    @GetMapping
    fun ping() = PingResponse("0.0.1").let { ResponseEntity.ok(it) }
}