package com.amitesh.catlogservice.controller.util

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.entity.Course

fun getDemoCourseList(): List<Course> = (1..100).map { index->
    Course(
        id = null,
        name = "Course $index",
        category = "Category $index"
    )
}

fun courseDTO(
    id: Int? = null,
    name: String = "Amitesh",
    category: String = "Development",
//    instructorId: Int? = 1
) = CourseDTO(
    id,
    name,
    category,
//    instructorId
)
