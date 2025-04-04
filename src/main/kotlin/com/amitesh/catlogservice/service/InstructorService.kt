package com.amitesh.catlogservice.service

import com.amitesh.catlogservice.dto.InstructorDTO
import com.amitesh.catlogservice.entity.Instructor
import com.amitesh.catlogservice.repository.InstructorRepository
import com.amitesh.catlogservice.service.mapper.toInstructor
import com.amitesh.catlogservice.service.mapper.toInstructorDTO
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun createInstructor(instructorDTO: InstructorDTO): InstructorDTO {
        return instructorRepository.save(instructorDTO.toInstructor()).toInstructorDTO()
    }

    fun findByInstructor(instructorId: Int):  Optional<Instructor> {
        return instructorRepository.findById(instructorId)
    }
}
