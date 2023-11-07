package com.yargu.studs.service

import com.yargu.studs.dto.StudentCreateWithoutImageDTO
import com.yargu.studs.dto.StudentInfoWithoutImageDTO
import com.yargu.studs.entity.CourseWork
import com.yargu.studs.entity.Department
import com.yargu.studs.entity.GraduateWork
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

    override fun findAll(): List<StudentInfoWithoutImageDTO> {
        val students = studentRepository.findAll()
        val departments = departmentRepository.findAll().associateBy { it.id }
        val courseWorks = courseWorkRepository.findAll().associateBy { it.id }
        val graduateWorks = graduateWorkRepository.findAll().associateBy { it.id }

        val allStudentsInDetail = mutableListOf<StudentInfoWithoutImageDTO>()
        for (student in students) {
            val department = departments[student.departmentId]
            val courseWork = courseWorks[student.courseWorkId]
            val graduateWork = graduateWorks[student.graduateWorkId]
            val studentInDetail = StudentInfoWithoutImageDTO(
                student.fullName,
                student.studNumber,
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


    override fun findById(id: Long): StudentInfoWithoutImageDTO? {
        val student = studentRepository.findById(id).get()
        val department = student.departmentId?.let { departmentRepository.findById(it) }?.get()
        val courseWork = student.courseWorkId?.let { courseWorkRepository.findById(it) }?.get()
        val graduateWork = student.graduateWorkId?.let { graduateWorkRepository.findById(it) }?.get()
        return StudentInfoWithoutImageDTO(
            student.fullName,
            student.studNumber,
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
    }

    override fun add(student: StudentCreateWithoutImageDTO) {
        if (student.studNumber.isNullOrEmpty()) {
            throw RuntimeException("StudNumber is empty")
        }
        if (!studentRepository.findByStudNumber(student.studNumber).isNullOrEmpty()) {
            throw RuntimeException("Student with same studNumber exist")
        }
        val studentInfo = Student()
        val department = Department()
        val departmentFaculty = student.departmentFaculty
        val departmentHead = student.departmentHead
        val departmentName = student.departmentName
        if (!departmentFaculty.isNullOrEmpty() || !departmentHead.isNullOrEmpty() || !departmentName.isNullOrEmpty()) {
            department.name = departmentName
            department.headOfDepartment = departmentHead
            department.faculty = departmentFaculty
            val departmentSaved = departmentRepository.save(department)
            studentInfo.departmentId = departmentSaved.id
        }
        val courseWork = CourseWork()
        val courseWorkGrade = student.courseWorkGrade
        val courseWorkTheme = student.courseWorkTheme
        if (courseWorkGrade != null || !courseWorkTheme.isNullOrEmpty()) {
            courseWork.grade = courseWorkGrade
            courseWork.name = courseWorkTheme
            val courseWorkSaved = courseWorkRepository.save(courseWork)
            studentInfo.courseWorkId = courseWorkSaved.id
        }
        val graduateWork = GraduateWork()
        val graduateWorkGrade = student.graduateWorkGrade
        val graduateWorkTheme = student.graduateWorkTheme
        if (graduateWorkGrade != null || !graduateWorkTheme.isNullOrEmpty()) {
            graduateWork.grade = graduateWorkGrade
            graduateWork.name = graduateWorkTheme
            val graduateWorkSaved = graduateWorkRepository.save(graduateWork)
            studentInfo.graduateWorkId = graduateWorkSaved.id
        }
        studentInfo.endAt = student.endAt
        studentInfo.studNumber = student.studNumber
        studentInfo.fullName = student.fullName
        studentInfo.educationLevel = student.educationLevel
        studentInfo.startedAt = student.startedAt
        studentInfo.successfullyGraduate = student.successfullyGraduate
        studentRepository.save(studentInfo)
    }
}