package com.yargu.studs.service

import com.yargu.studs.dto.StudentInfoDTO
import com.yargu.studs.entity.Student

interface StudentService {

    fun findAll(): List<StudentInfoDTO>

    fun findById(id: Long): Student?

    fun save(student: Student)
}