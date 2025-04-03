package com.amitesh.catlogservice.service.mapper

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.entity.Course

fun CourseDTO.toCourse(): Course {
    return Course(
        id = this.id,
        name = this.name,
        category = this.category
    )
}

fun Course.toCourseDTO(): CourseDTO {
    return CourseDTO(
        id = this.id,
        name = this.name,
        category = this.category
    )
}

fun List<Course>.toCourseDTO(): List<CourseDTO>{
    return this.map {
        it.toCourseDTO()
    }
}

fun List<CourseDTO>.toCourse(): List<Course>{
    return this.map {
        it.toCourse()
    }
}