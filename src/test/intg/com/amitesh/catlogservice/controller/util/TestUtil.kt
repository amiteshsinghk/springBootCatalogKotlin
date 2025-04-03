package com.amitesh.catlogservice.controller.util

import com.amitesh.catlogservice.entity.Course

fun getDemoCourseList(): List<Course> = (1..100).map { index->
    Course(
        id = null,
        name = "Course $index",
        category = "Category $index"
    )
}

//fun getDemoCourseList()= listOf(
//    Course(null,
//        "Build RestFul APis using SpringBoot and Kotlin", "Development"),
//    Course(null,
//        "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development"
//        ,
//    ),
//    Course(null,
//        "Wiremock for Java Developers", "Development" ,
//    )
//)