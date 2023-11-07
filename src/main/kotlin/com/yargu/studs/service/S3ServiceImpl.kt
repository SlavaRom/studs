package com.yargu.studs.service

import com.amazonaws.AmazonServiceException
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.apache.commons.io.FileUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.file.Files


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

    override fun getObject(name: String?) {
        try {
            val fullObject = s3.getObject(bucketName, name)
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

    override fun addImage(targetPath: String, inputStream: InputStream) {
        try {
            val tempFile = Files.createTempFile("s3-", "-$targetPath").toFile()
            try {
                FileUtils.copyInputStreamToFile(inputStream, tempFile)
                s3.putObject(bucketName, targetPath, tempFile)
            } finally {
                FileUtils.deleteQuietly(tempFile)
            }
        } catch (e: AmazonServiceException) {
            logger.warn(e.errorMessage)
        }
        logger.info("Object $targetPath putted into $bucketName")
    }

    override fun getImage(targetPath: String): InputStream {
        val responseInputStream = s3.getObject(bucketName, targetPath).objectContent
//        IOUtils.copy(responseInputStream, outputStream)
        return responseInputStream
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