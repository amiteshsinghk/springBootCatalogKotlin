package com.amitesh.catlogservice.controller

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.entity.Course
import com.amitesh.catlogservice.repository.CourseRepository
import com.amitesh.catlogservice.util.getDemoCourseList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class
CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    lateinit var coursesList: List<Course>

    @BeforeEach
    fun setup() {
        courseRepository.deleteAll()
        coursesList = getDemoCourseList()
        courseRepository.saveAll(coursesList)
    }

    @Test
    fun addCourse() {
        val courseDto = CourseDTO(
            id = null,
            name = "Build Restful APIs using SpringBoot and Kotlin",
            category = "Amitesh Singh"
        )

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
    fun retrieveAllCourses() {
        val courseDTOList = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(coursesList.size, courseDTOList?.size)
    }

    @Test
    fun retrieveCoursesByName() {
        val endPoint = UriComponentsBuilder
            .fromUriString("/v1/courses")
            .queryParam("course_name","Course2222")
            .toUriString()
        val courseDTOList = webTestClient
            .get()
            .uri(endPoint)
            .exchange()
            .expectStatus()
            .isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        assertEquals(1, courseDTOList?.size)
    }

    @Test
    fun updateCourse() {
        val course = Course(id = null, name = "Amitesh", category = "Android Development")
        courseRepository.save(course)
        val courseDTO = CourseDTO(id = null, name = "Amitesh Singh", category = "Android Development")
        val updatedCourse = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", course.id)
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus()
            .isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        assertEquals("Amitesh Singh", updatedCourse?.name)
    }

    @Test
    fun deleteCourse() {
        val course = Course(
            id = null,
            name = "Amitesh",
            category = "Android Development"
        )
        courseRepository.save(course)
        webTestClient
            .delete()
            .uri("v1/courses/{courseId}", course.id)
            .exchange()
            .expectStatus()
            .isNoContent
    }
}