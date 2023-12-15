package com.yargu.studs.dto

import java.io.Serializable
import java.util.*

class StudentInfoWithoutImageDTO (
    val fullName: String?,
    val studNumber: String?,
    val departmentName: String?,
    val departmentFaculty: String?,
    val departmentHead: String?,
    val startedAt: Date?,
    val educationLevel: String?,
    val courseWorkGrade: Int?,
    val courseWorkTheme: String?,
    val graduateWorkGrade: Int?,
    val graduateWorkTheme: String?,
    val endAt: Date?,
    val successfullyGraduate: Boolean?,
    val deleted: Boolean?
) : Serializable