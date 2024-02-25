package com.example.be_study.service.s3.service;

import com.example.be_study.service.s3.config.AwsConfig;
import com.example.be_study.service.s3.enums.HouseS3Bucket;
import com.example.be_study.service.s3.exception.S3ImageUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.net.URI;


@Slf4j
@Service
public class S3Service {

    private final AwsConfig awsConfig;

    public S3Service(AwsConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    private S3Client newS3Client() {
        return S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .endpointOverride(URI.create(awsConfig.getBaseUrl()))
                .credentialsProvider(() -> AwsBasicCredentials.create(awsConfig.getAccessKey(), awsConfig.getSecretKey()))
                .build();
    }

    /**
     * 공용 이미지 자원을 업로드시 사용합니다.
     */
    public String uploadFile(
            MultipartFile multipartFile,
            HouseS3Bucket houseS3Bucket) {
        try {
            S3Client s3Client = newS3Client();

            RequestBody requestBody = RequestBody
                    .fromInputStream(multipartFile.getInputStream(), multipartFile.getSize());

            PutObjectResponse response = s3Client.putObject(
                    PutObjectRequest.builder()
                            .contentType(multipartFile.getContentType())
                            .bucket(houseS3Bucket.getBucketName())
                            .key(houseS3Bucket.getS3Path(multipartFile.getOriginalFilename()))
                            .build(),
                    requestBody
            );

            if (!response.sdkHttpResponse().isSuccessful()) {
                log.error("Error response: " + response.sdkHttpResponse());
            }

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
     * 공용 이미지 자원을 업로드시 사용합니다.
     */
    public byte[] downloadFile(
            HouseS3Bucket houseS3Bucket,
            String pathName
    ) {
        S3Client s3Client = newS3Client();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(houseS3Bucket.getBucketName())
                .key(pathName)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
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
