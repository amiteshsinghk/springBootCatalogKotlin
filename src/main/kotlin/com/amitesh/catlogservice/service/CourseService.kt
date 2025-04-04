package com.amitesh.catlogservice.service

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.exception.CourseNotFoundException
import com.amitesh.catlogservice.exception.InstructorNotValidException
import com.amitesh.catlogservice.repository.CourseRepository
import com.amitesh.catlogservice.service.mapper.toCourse
import com.amitesh.catlogservice.service.mapper.toCourseDTO
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository,
    private val instructorService: InstructorService) {


    companion object {
        val logger = KLogging().logger
    }

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val optionalInstructor = courseDTO.instructorId?.let { instructorService.findByInstructor(it) }
        if (optionalInstructor == null || optionalInstructor.isEmpty){
            throw InstructorNotValidException("Instructor Not Valid for the Id: ${courseDTO.instructorId}")
        }
        val course = courseRepository.save(courseDTO.toCourse(optionalInstructor.get()))
        return course.toCourseDTO()
    }

    fun retrieveAllCourses(courseName: String?): List<CourseDTO> {
        val courseList = courseName?.takeIf { it.isNotBlank() }
            ?.let { courseRepository.findCoursesByName(it) }
            ?: courseRepository.findAll()
       return courseList.map { it.toCourseDTO() }
    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val existCourse = courseRepository.findById(courseId)
        if (existCourse.isPresent) {
            val updateCourse = existCourse.get().copy(
                name = courseDTO.name,
                category = courseDTO.category
            )
            return courseRepository.save(updateCourse).toCourseDTO()
        } else {
            throw CourseNotFoundException("The provided ID does not match any course. $courseId ")
        }
    }

    fun deleteCourse(courseId: Int) {
        val exist = courseRepository.findById(courseId)
        if (exist.isPresent) {
            exist.get().let { courseRepository.deleteById(courseId) }
        } else {
            throw CourseNotFoundException("The provided ID does not match any course. $courseId ")
        }
    }
}

