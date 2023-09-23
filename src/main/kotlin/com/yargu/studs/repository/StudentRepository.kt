package com.yargu.studs.repository

import com.yargu.studs.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    fun findByStudNumber(studsNumber: String): List<Student>
}