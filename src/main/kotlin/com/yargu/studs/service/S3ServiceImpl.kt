package com.yargu.studs.service

import com.amazonaws.AmazonServiceException
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream




@Service
class S3ServiceImpl(
    @Value("\${amazon.s3.bucketName}") var bucketName: String
) : S3Service {

    override fun listBucketObjects(): List<String> {
        return try {
            s3.listObjectsV2(bucketName).objectSummaries.map { it.key }
        } catch (e: AmazonServiceException) {
            logger.warn(e.errorMessage)
            emptyList()
        }
    }

    override fun putObjectToBucket(file: File) {
        try {
            s3.putObject(bucketName, file.name, file)
//            s3.putObject(bucketName, Paths.get("/Users/vyacheslavromanov/IdeaProjects/8CaCtaVcGrU.jpg").fileName.toString(), File("/Users/vyacheslavromanov/IdeaProjects/8CaCtaVcGrU.jpg"))
        } catch (e: AmazonServiceException) {
            logger.warn(e.errorMessage)
        }
        logger.info("Object ${file.name} putted into $bucketName")
    }

    override fun getObject(name: String) {
        try {
            val fullObject = s3.getObject(bucketName, "8CaCtaVcGrU.jpg")
            val inputStream = fullObject.objectContent
            val outputFile = File("/Users/vyacheslavromanov/IdeaProjects/studs/src/main/resources/static/output.jpg")
            val outputStream = FileOutputStream(outputFile)

            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: AmazonServiceException) {
            logger.warn(e.errorMessage)
        }
    }

    companion object {
        val s3: AmazonS3 = AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    "storage.yandexcloud.net", "ru-central1"
                )
            )
            .build()
        val logger: Logger = LoggerFactory.getLogger(S3ServiceImpl::class.java)
    }
}