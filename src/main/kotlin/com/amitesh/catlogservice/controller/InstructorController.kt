package com.amitesh.catlogservice.controller

import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.service.InstructorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/instructor")
@Validated
class InstructorController(val instructorService: InstructorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstructor(@RequestBody @Valid instructorDTO: InstructorDTO) = instructorService.createInstructor(instructorDTO)
}