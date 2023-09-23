package com.yargu.studs.service

import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import com.yargu.studs.dto.StudentInfoDTO

interface StudentService {

    fun findAll(): List<StudentInfoDTO>

    fun findById(id: Long): StudentInfoDTO?

    fun add(student: StudentCreateWithoutImageDTO)
}