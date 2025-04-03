package com.amitesh.catlogservice

import com.amitesh.catlogservice.controller.CourseController
import com.amitesh.catlogservice.controller.util.courseDTO
import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.service.CourseService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@WebMvcTest(controllers = [CourseController::class])
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var courseServiceMockk: CourseService

    @Test
    fun addCourse() {
        val courseDto = CourseDTO(
            id = null,
            name = "Amitesh",
            category = "Development"
        )

        every { courseServiceMockk.addCourse(any()) } returns courseDTO(id = 1)

        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDto)
            .exchange()
            .expectStatus()
            .isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertTrue {
            savedCourseDTO?.id != null
        }
    }

    @Test
    fun retrieveAllCourses(){
        every {
            courseServiceMockk.retrieveAllCourses()
        }.returnsMany(
            listOf(courseDTO(id = 1),
                courseDTO(id = 2)
            )
        )
        val courseDTOList = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(2,courseDTOList?.size)
    }

}