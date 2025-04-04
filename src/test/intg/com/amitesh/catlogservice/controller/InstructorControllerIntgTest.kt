package com.amitesh.catlogservice.controller

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.repository.InstructorRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class InstructorControllerIntgTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun createInstructorIntgTest(){
        val instructorDto = InstructorDTO(
            id = null,
            name = "Amitesh Singh"
        )
        val savedInstructorDto = webTestClient
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
            savedInstructorDto?.id != null
        }
    }
}