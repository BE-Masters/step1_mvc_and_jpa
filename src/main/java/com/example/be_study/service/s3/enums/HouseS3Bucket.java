package com.example.be_study.service.s3.enums;

import lombok.Getter;

@Getter
public enum HouseS3Bucket {

    PolicyPdfBucket("policy-pdf-bucket", "policy-pdf/");

    private String bucketName;
    private String s3Path;

    HouseS3Bucket(String bucketName, String s3Path) {
        this.bucketName = bucketName;
        this.s3Path = s3Path;
    }

    public String getS3UserPath(Long userId, String fileName) {
        return "/userId=" + userId + s3Path + fileName;
    }

    public String getS3Path(String fileName) {
        return s3Path + fileName;
    }
}
