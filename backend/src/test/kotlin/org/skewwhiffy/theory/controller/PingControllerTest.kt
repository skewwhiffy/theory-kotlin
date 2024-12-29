package org.skewwhiffy.theory.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.skewwhiffy.theory.org.skewwhiffy.theory.dto.PingResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import com.fasterxml.jackson.module.kotlin.readValue
import org.assertj.core.api.Assertions.assertThat

@WebMvcTest
class PingControllerTest(
    @Autowired
    private val mvc: MockMvc,
    @Autowired
    private val objectMapper: ObjectMapper
) {
    @Test
    fun `Returns version number`() {
        val rawPayload = mvc
            .perform(get("/api/ping"))
            .andExpect(status().isOk())
            .andReturn()
            .response
            .contentAsString

        val deserialized = objectMapper.readValue<PingResponse>(rawPayload)
        assertThat(deserialized.version).isNotNull()
    }
}