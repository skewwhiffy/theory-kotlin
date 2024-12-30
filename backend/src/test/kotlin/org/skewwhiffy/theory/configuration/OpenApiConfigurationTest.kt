package org.skewwhiffy.theory.configuration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.File

@SpringBootTest
@AutoConfigureMockMvc
class OpenApiConfigurationTest(
    @Autowired private val mvc: MockMvc
) {
    @Test
    fun `Generate open api spec`() {
        val rawPayload = mvc
            .perform(get("/v3/api-docs.yaml"))
            .andExpect(status().isOk())
            .andReturn()
            .response
            .contentAsString

        with(File("api-docs.yaml")) { writeText(rawPayload) }
    }
}