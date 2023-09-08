package com.yargu.studs.repository

import com.yargu.studs.entity.CourseWork
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseWorkRepository : JpaRepository<CourseWork, Long> {

}