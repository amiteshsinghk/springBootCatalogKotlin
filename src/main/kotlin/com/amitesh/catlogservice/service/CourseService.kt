package com.amitesh.catlogservice.service

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.exception.CourseNotFoundException
import com.amitesh.catlogservice.repository.CourseRepository
import com.amitesh.catlogservice.service.mapper.toCourse
import com.amitesh.catlogservice.service.mapper.toCourseDTO
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {


    companion object {
        val logger = KLogging().logger
    }

    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val course = courseRepository.save(courseDTO.toCourse())
        return course.toCourseDTO()
    }

    fun retrieveAllCourses(): List<CourseDTO> {
        return courseRepository.findAll().map { it.toCourseDTO() }
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

