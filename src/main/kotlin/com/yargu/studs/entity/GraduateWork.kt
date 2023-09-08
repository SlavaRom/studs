package com.yargu.studs.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class GraduateWork {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null

    var name: String? = null

    var grade: Int? = null
}