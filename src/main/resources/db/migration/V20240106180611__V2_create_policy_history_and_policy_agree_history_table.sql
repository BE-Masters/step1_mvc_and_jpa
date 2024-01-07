CREATE TABLE policy_history (
  policy_history_id BIGINT unsigned PRIMARY KEY COMMENT '이용약관 개정 아이디',
  policy_type VARCHAR(100) NOT NULL COMMENT '이용약관 타입',
  revision_date DATETIME NOT NULL COMMENT '이용약관 개정일',
  pdf_file_path VARCHAR(300) NOT NULL COMMENT '개정된 이용약관 PDF 파일 경로',
  required BOOL NOT NULL default false COMMENT '필수 약관 여부',
  is_latest_revision BOOL NOT NULL default false COMMENT '최종 개정판 여부',
  created_at DATETIME NOT NULL COMMENT '생성일',
  modified_at DATETIME NOT NULL COMMENT '수정일',
  created_by BIGINT unsigned NOT NULL COMMENT '생성한 유저',
  modified_by BIGINT unsigned NOT NULL COMMENT '수정한 유저'
);

CREATE TABLE policy_agree (
  policy_agree_id BIGINT unsigned PRIMARY KEY COMMENT '이용약관 동의 id',
  policy_type VARCHAR(100) NOT NULL COMMENT '이용약관 타입',
  policy_history_id BIGINT unsigned NOT NULL COMMENT '이용약관 개정 id',
  user_id BIGINT unsigned NOT NULL COMMENT '동의한 유저 id',
  created_at DATETIME NOT NULL COMMENT '생성일',
  modified_at DATETIME NOT NULL COMMENT '수정일'
);
