package com.yargu.studs.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null

    var fullName: String? = null

    var startedAt: Date? = null

    var educationLevel: String? = null

    var departmentId: Long? = null

    var courseWorkId: Long? = null

    var graduateWorkId: Long? = null

    var endAt: Date? = null

    var successfullyGraduate: Boolean? = null
}