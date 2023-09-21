package com.yargu.studs.rest

import com.yargu.studs.dto.StudentCreateDTO
import com.yargu.studs.dto.StudentInfoDTO
import com.yargu.studs.entity.Student
import com.yargu.studs.service.StudentService
import com.yargu.studs.utils.StudentMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(
    private val studentService: StudentService,
    private val mapper: StudentMapper
) {

    @PostMapping
    fun addStudent(@RequestBody studentCreateDTO: StudentCreateDTO): ResponseEntity<Any?> {
        val student = mapper.map(studentCreateDTO, Student::class.java)
        return try {
            studentService.save(student)
            ResponseEntity(student, HttpStatus.OK)
        } catch (ex: Exception){
            ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping
    fun findAll(): List<StudentInfoDTO> {
        return studentService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Student? {
        return studentService.findById(id)
    }
}