package com.amitesh.catlogservice.repository

import com.amitesh.catlogservice.entity.Course
import com.amitesh.catlogservice.util.getDemoCourseList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class CourseRepositoryIntgTest {

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
    fun findByNameContatining() {
        val courses = courseRepository.findByNameContaining("Course2222")
        println("courses :: $courses")
        Assertions.assertEquals(1, courses.size)
    }

    @Test
    fun findCoursesByName() {
        val courses = courseRepository.findCoursesByName("Course2222")
        println("courses :: $courses")
        Assertions.assertEquals(1, courses.size)
    }

}