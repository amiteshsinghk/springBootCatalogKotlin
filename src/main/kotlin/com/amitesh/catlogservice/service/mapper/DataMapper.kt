package com.amitesh.catlogservice.service.mapper

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.entity.Course
import com.amitesh.catlogservice.entity.Instructor

fun CourseDTO.toCourse(instructor: Instructor): Course {
    return Course(
        id = this.id,
        name = this.name,
        category = this.category,
        instructor = instructor
    )
}

fun Course.toCourseDTO(): CourseDTO {
    return CourseDTO(
        id = this.id,
        name = this.name,
        category = this.category,
        instructorId = this.instructor.id
    )
}

fun List<Course>.toCourseDTO(): List<CourseDTO>{
    return this.map {
        it.toCourseDTO()
    }
}

fun List<CourseDTO>.toCourse(instructor: Instructor): List<Course>{
    return this.map {
        it.toCourse(instructor)
    }
}

fun InstructorDTO.toInstructor():Instructor{
    return Instructor(
        id = this.id,
        name = this.name
    )
}

fun Instructor.toInstructorDTO():InstructorDTO{
    return InstructorDTO(
        id = this.id,
        name = this.name
    )
}