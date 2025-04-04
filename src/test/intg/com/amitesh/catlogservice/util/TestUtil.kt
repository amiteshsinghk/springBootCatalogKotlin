package com.amitesh.catlogservice.util

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.entity.Course
import com.amitesh.catlogservice.entity.Instructor

fun getDemoCourseList(instructor: Instructor): List<Course> = (1..100).map { index ->
    Course(
        id = null,
        name = if (index == 2) "Course2222 $index" else "Course $index",
        category = "Category $index",
        instructor = instructor
    )
}

fun courseDTO(
    id: Int? = null,
    name: String = "Amitesh",
    category: String = "Development",
    instructorId: Int? = 1
) = CourseDTO(
    id,
    name,
    category,
    instructorId
)

fun instructorDTO(
    id: Int? = null,
    name: String = "Amitesh Singh"
) = InstructorDTO(
    id,
    name
)

