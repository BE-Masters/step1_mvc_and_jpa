DROP TABLE IF EXISTS user;
CREATE TABLE user
(
	user_id 		bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '아이디',
	user_email 		varchar(100) NULL UNIQUE COMMENT '이메일',
	user_profile            longtext NULL COMMENT '유저 프로필 이미지',
	user_password  		varchar(255) NULL COMMENT '패스워드',
	user_type               varchar(30) NOT NULL default 'user' COMMENT '사용자 타입(사용자, 관리자)',
	user_nickname 		varchar(30) NOT NULL UNIQUE COMMENT '유저 닉네임',
	provider_type           varchar(30) NOT NULL COMMENT '로그인 타입(KAKAO, NAVER, Facebook, Origin)',
	provider_key            varchar(255) NOT NULL UNIQUE COMMENT '로그인 타입에 따른 식별값',
	created_at 		datetime NOT NULL COMMENT '유저 회원가입 생성일',
	modified_at             datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '유저 정보 수정일',
	user_last_login_date    datetime NOT NULL COMMENT '유저 마지막 로그인 일자',
	deleted                 boolean NOT NULL default false COMMENT '회원탈퇴 여부',
	dormancy	        boolean NOT NULL default false COMMENT '유저 휴면 여부',
	INDEX idx_email (user_email) COMMENT '유저 이메일 인덱싱',
	INDEX idx_nickname (user_nickname) COMMENT '유저 닉네임 인덱싱',
	INDEX idx_provider_key (provider_key) COMMENT '유저 식별값'
) ENGINE = InnoDB
DEFAULT CHARSET = utf8mb4
COMMENT = '유저 테이블';
