package com.yargu.studs.rest

import com.yargu.studs.service.S3Service
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/s3")
class S3Controller(
    private val s3Service: S3Service
) {

    @GetMapping
    fun findAll(): ResponseEntity<Any> {
        val objects = s3Service.listBucketObjects()
        logger.info("Count objects in bucket - ${objects.count()}")
        s3Service.getObject(objects.last())
        return ResponseEntity(objects, HttpStatus.OK)
    }

    companion object {
        val logger = LoggerFactory.getLogger(S3Controller::class.java)
    }
}