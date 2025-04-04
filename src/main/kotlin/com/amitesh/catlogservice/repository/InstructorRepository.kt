package com.amitesh.catlogservice.repository

import com.amitesh.catlogservice.entity.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository: CrudRepository<Instructor, Int>