package com.amitesh.catlogservice.repository

import com.amitesh.catlogservice.dto.CourseDTO
import com.amitesh.catlogservice.entity.Course
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CourseRepository: CrudRepository<Course, Int>{
//    Custom query using JPA
//    https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.query-creation
    fun findByNameContaining(name: String): List<Course>

//    Custon native Query
    @Query(value = "SELECT * FROM COURSES where name like %?1%", nativeQuery = true)
    fun findCoursesByName(name: String): List<Course>
}