package com.amitesh.catlogservice.dto

import jakarta.validation.constraints.NotBlank

data class InstructorDTO(
    val id: Int?,
    @get:NotBlank(message = "InstructorDTO :: name must not blank")
    val name: String
)
