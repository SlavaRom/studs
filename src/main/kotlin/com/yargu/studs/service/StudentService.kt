package com.yargu.studs.service

import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import com.yargu.studs.dto.StudentInfoWithoutImageDTO

interface StudentService {

    fun findAll(): List<StudentInfoWithoutImageDTO>

    fun findById(id: Long): StudentInfoWithoutImageDTO?

    fun add(student: StudentCreateWithoutImageDTO)
}