package com.yargu.studs.rest

import com.yargu.studs.dto.StudentCreateWithImageDTO
import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import com.yargu.studs.dto.StudentInfoWithImageDTO
import com.yargu.studs.dto.StudentInfoWithoutImageDTO
import com.yargu.studs.service.S3Service
import com.yargu.studs.service.StudentService
import io.swagger.v3.oas.annotations.Operation
import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.nio.file.Files

@RestController
@RequestMapping("/student")
class StudentController(
    private val studentService: StudentService,
    private val s3Service: S3Service
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
    fun findAllWithoutImage(): List<StudentInfoWithoutImageDTO> {
        logger.info("Request to show all students")
        return studentService.findAll()
    }

    @Operation(summary = "Student by id without image.")
    @GetMapping("/{id}")
    fun findByIdWithoutImage(@PathVariable id: Long): StudentInfoWithoutImageDTO? {
        logger.info("Request to show student by id = $id")
        return studentService.findById(id)
    }

    @Operation(summary = "Add new student with image", description = "All fields except studNumber and image are optional. studNumber unique field.")
    @PostMapping("/image")
    fun addStudentWithImage(@RequestBody studentCreateWithImageDTO: StudentCreateWithImageDTO): ResponseEntity<Any?> {
        logger.info("Request to add student")
        return try {
            val studentCreateWithoutImageDTO = StudentCreateWithoutImageDTO(
                studentCreateWithImageDTO.fullName,
                studentCreateWithImageDTO.studNumber,
                studentCreateWithImageDTO.departmentName,
                studentCreateWithImageDTO.departmentFaculty,
                studentCreateWithImageDTO.departmentHead,
                studentCreateWithImageDTO.startedAt,
                studentCreateWithImageDTO.educationLevel,
                studentCreateWithImageDTO.courseWorkGrade,
                studentCreateWithImageDTO.courseWorkTheme,
                studentCreateWithImageDTO.graduateWorkGrade,
                studentCreateWithImageDTO.graduateWorkTheme,
                studentCreateWithImageDTO.endAt,
                studentCreateWithImageDTO.successfullyGraduate
            )
            studentService.add(studentCreateWithoutImageDTO)
            s3Service.addImage(studentCreateWithImageDTO.studNumber!!, studentCreateWithImageDTO.image.inputStream)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: Exception){
            ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @Operation(summary = "Student by id with image.")
    @GetMapping("/image/{id}")
    fun findByIdWithImage(@PathVariable id: Long): StudentInfoWithImageDTO? {
        logger.info("Request to show student by id = $id")
        val studentInfoWithoutImageDTO = studentService.findById(id) ?: return null
        val inputStream = s3Service.getImage(studentInfoWithoutImageDTO.studNumber!!)
        val studentInfoWithImageDTO = StudentInfoWithImageDTO(
            studentInfoWithoutImageDTO.fullName,
            studentInfoWithoutImageDTO.studNumber,
            studentInfoWithoutImageDTO.departmentName,
            studentInfoWithoutImageDTO.departmentFaculty,
            studentInfoWithoutImageDTO.departmentHead,
            studentInfoWithoutImageDTO.startedAt,
            studentInfoWithoutImageDTO.educationLevel,
            studentInfoWithoutImageDTO.courseWorkGrade,
            studentInfoWithoutImageDTO.courseWorkTheme,
            studentInfoWithoutImageDTO.graduateWorkGrade,
            studentInfoWithoutImageDTO.graduateWorkTheme,
            studentInfoWithoutImageDTO.endAt,
            studentInfoWithoutImageDTO.successfullyGraduate,
            null
        )
        val tempFile = Files.createTempFile("s3-", "-${studentInfoWithoutImageDTO.studNumber!!}").toFile()
        FileUtils.copyInputStreamToFile(inputStream, tempFile)
        studentInfoWithImageDTO.image = tempFile
        FileUtils.deleteQuietly(tempFile)
        return studentInfoWithImageDTO
    }

    companion object {
        val logger = LoggerFactory.getLogger(StudentController::class.java)
    }
}