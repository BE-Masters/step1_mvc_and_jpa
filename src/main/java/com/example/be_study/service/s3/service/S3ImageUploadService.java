package com.example.be_study.service.s3.service;

import com.example.be_study.service.s3.enums.HouseS3Bucket;
import com.example.be_study.service.s3.exception.S3ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;


@Service
public class S3ImageUploadService {

    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.s3.baseUrl}")
    private String baseUrl;


    /**
     * 공용 이미지 자원을 업로드시 사용합니다.
     */
    public String uploadImage(
            MultipartFile multipartFile,
            HouseS3Bucket houseS3Bucket) {
        try {

            S3Client s3Client = S3Client.builder()
                    .region(Region.AP_NORTHEAST_2)
                    .endpointOverride(URI.create(baseUrl))
                    .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
                    .build();

            RequestBody requestBody = RequestBody
                    .fromInputStream(multipartFile.getInputStream(), multipartFile.getSize());

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .contentType(multipartFile.getContentType())
                            .bucket(houseS3Bucket.getBucketName())
                            .key(houseS3Bucket.getS3Path(multipartFile.getOriginalFilename()))
                            .build(),
                    requestBody
            );


            return houseS3Bucket.getS3Path(multipartFile.getOriginalFilename());
        } catch (IOException e) {
            throw new S3ImageUploadException(
                    HttpStatusCode.valueOf(400),
                    " 해당 유저의 S3 Image Upload에 실패했습니다." + e.getMessage(),
                    e
            );
        }
    }

    /**
     * 유저 개인의 이미지 자원을 업로드시 사용합니다.
     */
    public String uploadUserImage(
            MultipartFile multipartFile,
            HouseS3Bucket houseS3Bucket) {

        //TODO 구현필요
        return null;
    }
}
