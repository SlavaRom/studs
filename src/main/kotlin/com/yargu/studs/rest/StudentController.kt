package com.yargu.studs.rest

import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import com.yargu.studs.dto.StudentInfoDTO
import com.yargu.studs.service.StudentService
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/student")
class StudentController(
    private val studentService: StudentService
) {

    @Operation(summary = "Add new student without image", description = "All fields except studNumber are optional. studNumber unique field.")
    @PostMapping
    fun addStudentWithoutImage(@RequestBody studentCreateWithoutImageDTO: StudentCreateWithoutImageDTO): ResponseEntity<Any?> {
        logger.info("Request to add student")
        return try {
            studentService.add(studentCreateWithoutImageDTO)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: Exception){
            ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "List students without images.")
    @GetMapping
    fun findAllWithoutImage(): List<StudentInfoDTO> {
        logger.info("Request to show all students")
        return studentService.findAll()
    }

    @Operation(summary = "Student by id without image.")
    @GetMapping("/{id}")
    fun findByIdWithoutImage(@PathVariable id: Long): StudentInfoDTO? {
        logger.info("Request to show student by id = $id")
        return studentService.findById(id)
    }

    @Operation(summary = "Add new student with image", description = "All fields except studNumber are optional. studNumber unique field.")
    @PostMapping
    fun addStudentWithImage(@RequestBody studentCreateWithoutImageDTO: StudentCreateWithoutImageDTO): ResponseEntity<Any?> {
        logger.info("Request to add student")
        return try {
            studentService.add(studentCreateWithoutImageDTO)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: Exception){
            ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(StudentController::class.java)
    }
}