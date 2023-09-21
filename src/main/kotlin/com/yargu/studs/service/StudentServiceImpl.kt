package com.yargu.studs.service

import com.yargu.studs.dto.StudentInfoDTO
import com.yargu.studs.entity.Student
import com.yargu.studs.repository.CourseWorkRepository
import com.yargu.studs.repository.DepartmentRepository
import com.yargu.studs.repository.GraduateWorkRepository
import com.yargu.studs.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository,
    private val departmentRepository: DepartmentRepository,
    private val courseWorkRepository: CourseWorkRepository,
    private val graduateWorkRepository: GraduateWorkRepository
) : StudentService {

    override fun findAll(): List<StudentInfoDTO> {
        val students = studentRepository.findAll()
        val departments = departmentRepository.findAll().associateBy { it.id }
        val courseWorks = courseWorkRepository.findAll().associateBy { it.id }
        val graduateWorks = graduateWorkRepository.findAll().associateBy { it.id }

        val allStudentsInDetail = mutableListOf<StudentInfoDTO>()
        for (student in students) {
            val department = departments[student.departmentId]
            val courseWork = courseWorks[student.courseWorkId]
            val graduateWork = graduateWorks[student.graduateWorkId]
            val studentInDetail = StudentInfoDTO(
                student.fullName,
                department?.name,
                department?.faculty,
                department?.headOfDepartment,
                student.startedAt,
                student.educationLevel,
                courseWork?.grade,
                courseWork?.name,
                graduateWork?.grade,
                graduateWork?.name,
                student.endAt,
                student.successfullyGraduate
            )
            allStudentsInDetail.add(studentInDetail)
        }
        return allStudentsInDetail.toList()
    }

    override fun findById(id: Long): Student? {
        return studentRepository.findById(id).get()
    }

    override fun save(student: Student) {
        studentRepository.save(student)
    }
}