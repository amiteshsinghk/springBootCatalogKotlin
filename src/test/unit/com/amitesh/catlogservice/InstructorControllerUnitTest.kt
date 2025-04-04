package com.amitesh.catlogservice

import com.amitesh.catlogservice.controller.InstructorController
import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.service.InstructorService
import com.amitesh.catlogservice.util.instructorDTO
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertTrue

@WebMvcTest(controllers = [InstructorController::class])
@AutoConfigureWebTestClient
class InstructorControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var instructorServiceMockk: InstructorService

    @Test
    fun createInstructorUnitTest() {
        val instructorDto = InstructorDTO(
            id = null,
            name = "Amitesh Singh"
        )

        every { instructorServiceMockk.createInstructor(any()) } returns instructorDTO(id = 1)

        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/instructor")
            .bodyValue(instructorDto)
            .exchange()
            .expectStatus()
            .isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult()
            .responseBody

        assertTrue {
            savedCourseDTO?.id != null
        }
    }
}