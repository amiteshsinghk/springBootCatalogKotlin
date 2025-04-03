package com.amitesh.catlogservice.service

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.repository.CourseRepository
import com.amitesh.catlogservice.service.mapper.toCourse
import com.amitesh.catlogservice.service.mapper.toCourseDTO
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {


    companion object{
            val logger = KLogging().logger
        }
    fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val course = courseRepository.save(courseDTO.toCourse())
        logger.info("Saved Course Is $course")
        return course.toCourseDTO()
    }

    fun retrieveAllCourses(): List<CourseDTO>{
        return courseRepository.findAll().map { it.toCourseDTO() }
    }
}

