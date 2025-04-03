package com.amitesh.catlogservice.dto

import jakarta.validation.constraints.NotBlank

data class CourseDTO(
    val id: Int?,
    @get:NotBlank(message = "name must not blank")
    val name: String,
    @get:NotBlank(message = "category must not blank")
    val category: String
)
