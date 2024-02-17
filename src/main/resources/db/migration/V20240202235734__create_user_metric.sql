CREATE TABLE user_metric (
    user_id BIGINT PRIMARY KEY COMMENT '유저 아아디',
    age SMALLINT NULL COMMENT '유저 나이',
    device_type VARCHAR(20) NULL COMMENT '디바이스 타입 - AOS, IOS, WEB'
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE INDEX IDX_USER_ID
ON user_metric (user_id);
