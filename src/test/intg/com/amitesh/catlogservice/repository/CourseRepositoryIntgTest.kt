package com.amitesh.catlogservice.repository

import com.amitesh.catlogservice.entity.Course
import com.amitesh.catlogservice.service.mapper.toInstructor
import com.amitesh.catlogservice.util.PostgreSQLContainerInitializer
import com.amitesh.catlogservice.util.getDemoCourseList
import com.amitesh.catlogservice.util.instructorDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.util.stream.Stream

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryIntgTest: PostgreSQLContainerInitializer() {

    @Autowired
    lateinit var courseRepository: CourseRepository

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    lateinit var coursesList: List<Course>

    @BeforeEach
    fun setup() {
        courseRepository.deleteAll()
        val instructor = instructorRepository.save(instructorDTO().toInstructor())
        coursesList = getDemoCourseList(instructor)
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

    @ParameterizedTest
    @MethodSource("courseAndSize")
    fun findCoursesByName_approach2(name: String, expectedSize: Int) {
        val courses = courseRepository.findCoursesByName(name)
        println("courses :: $courses")
        Assertions.assertEquals(expectedSize, courses.size)
    }

    companion object {
        @JvmStatic
        fun courseAndSize(): Stream<Arguments> {
            return Stream.of(Arguments.arguments("Course2222", 1))
        }
    }

}