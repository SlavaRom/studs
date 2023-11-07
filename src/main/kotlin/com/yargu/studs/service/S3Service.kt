package com.yargu.studs.service

import java.io.File
import java.io.InputStream

interface S3Service {
    fun listBucketObjects(): List<String>
    fun putObjectToBucket(file: File)
    fun getObject(name: String?)
    fun addImage(targetPath: String, inputStream: InputStream)
    fun getImage(targetPath: String): InputStream
}