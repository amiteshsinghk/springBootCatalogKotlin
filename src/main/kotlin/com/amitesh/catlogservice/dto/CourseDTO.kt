package com.amitesh.catlogservice.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CourseDTO(
    val id: Int?,
    @get:NotBlank(message = "CourseDTO :: name must not blank")
    val name: String,
    @get:NotBlank(message = "CourseDTO :: category must not blank")
    val category: String,
    @get:NotNull(message = "CourseDTO :: instructorId must not blank")
    val instructorId: Int? = null
)
