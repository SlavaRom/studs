package com.yargu.studs.service

import com.yargu.studs.entity.Student
import com.yargu.studs.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(private val studentRepository: StudentRepository) : StudentService {

    override fun findAll(): List<Student> {
        return studentRepository.findAll()
    }

    override fun findById(id: Long): Student? {
        return studentRepository.findById(id).get()
    }

    override fun save(student: Student) {
        studentRepository.save(student)
    }
}