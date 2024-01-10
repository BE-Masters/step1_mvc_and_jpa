CREATE TABLE policy_history (
  policy_history_id BIGINT unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '이용약관 개정 아이디',
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

INSERT INTO policy_history (
  policy_type,
  revision_date,
  pdf_file_path,
  required,
  is_latest_revision,
  created_at,
  modified_at,
  created_by,
  modified_by
) VALUES (
  'REQUIRED_IS_OVER_FOURTEEN',
  '2024-01-10 22:00:00',
  'pdf_file',
  true,
  true,
  '2024-01-10 22:00:00',
  '2024-01-10 22:00:00',
  1,
  1
);

INSERT INTO policy_history (
  policy_type,
  revision_date,
  pdf_file_path,
  required,
  is_latest_revision,
  created_at,
  modified_at,
  created_by,
  modified_by
) VALUES (
  'REQUIRED_TERMS_OF_SERVICE',
  '2024-01-10 22:00:00',
  'pdf_file',
  true,
  true,
  '2024-01-10 22:00:00',
  '2024-01-10 22:00:00',
  1,
  1
);

INSERT INTO policy_history (
  policy_type,
  revision_date,
  pdf_file_path,
  required,
  is_latest_revision,
  created_at,
  modified_at,
  created_by,
  modified_by
) VALUES (
  'REQUIRED_PERSONAL_INFO_PROCESS',
  '2024-01-10 22:00:00',
  'pdf_file',
  true,
  true,
  '2024-01-10 22:00:00',
  '2024-01-10 22:00:00',
  1,
  1
);

INSERT INTO policy_history (
  policy_type,
  revision_date,
  pdf_file_path,
  required,
  is_latest_revision,
  created_at,
  modified_at,
  created_by,
  modified_by
) VALUES (
  'OPTION_PERSONAL_INFO_MARKETING',
  '2024-01-10 22:00:00',
  'pdf_file',
  false,
  true,
  '2024-01-10 22:00:00',
  '2024-01-10 22:00:00',
  1,
  1
);

INSERT INTO policy_history (
  policy_type,
  revision_date,
  pdf_file_path,
  required,
  is_latest_revision,
  created_at,
  modified_at,
  created_by,
  modified_by
) VALUES (
  'OPTION_EVENT_MAIL_OR_SMS',
  '2024-01-10 22:00:00',
  'pdf_file',
  true,
  true,
  '2024-01-10 22:00:00',
  '2024-01-10 22:00:00',
  1,
  1
);

CREATE TABLE policy_agree (
  policy_agree_id BIGINT unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '이용약관 동의 id',
  policy_type VARCHAR(100) NOT NULL COMMENT '이용약관 타입',
  policy_history_id BIGINT unsigned NOT NULL COMMENT '이용약관 개정 id',
  user_id BIGINT unsigned NOT NULL COMMENT '동의한 유저 id',
  created_at DATETIME NOT NULL COMMENT '생성일',
  modified_at DATETIME NOT NULL COMMENT '수정일'
);
