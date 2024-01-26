# 기본 설치 프로그램
1. Docker를 설치합니다.

# 사용하는 포트 numbers
1. 3306 : mysql
2. 4566 : localstack
3. 8080 : tomcat

# Docker Compose
1. localstack CLI 설치 -> brew install localstack/tap/localstack-cli


# DB (실행문 - 신규)
1. make start (docker를 실행합니다.)
2. make sto (docker를 종료합니다.)

# DB (legacy 실행문 - 아직 사용가능)
1. terminal에서 /infra 폴더로 이동합니다.
2. ```docker-compose up -d``` 를 실행합니다.

# Flyway
DB 테이블 형상을 관리하는 툴입니다.

1. DB를 아예 초기화 하고 새롭게 데이터를 쓰고 싶다면
flywayClean -> flywayMigrate

2. 새롭게 추가된 migrate만 적용하고싶다면 
flywayMigrate



