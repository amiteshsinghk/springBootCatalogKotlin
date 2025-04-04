package com.amitesh.catlogservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "Courses")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val name: String,
    val category: String,
    @ManyToOne(fetch= FetchType.LAZY)// It mean that this call will happen when you are pulling the instructor data otherwise it won't call.
    @JoinColumn(name="INSTRUCTOR_ID", nullable = false)// It means instructor value shouldn't be null when the course is created.
    val instructor: Instructor
)