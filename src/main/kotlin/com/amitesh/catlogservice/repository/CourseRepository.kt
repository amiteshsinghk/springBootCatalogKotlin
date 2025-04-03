package com.amitesh.catlogservice.repository

import com.amitesh.catlogservice.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Int> {
}