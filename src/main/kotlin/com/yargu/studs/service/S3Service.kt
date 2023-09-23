package com.yargu.studs.service

import java.io.File

interface S3Service {
    fun listBucketObjects(): List<String>
    fun putObjectToBucket(file: File)
    fun getObject(name: String)
}