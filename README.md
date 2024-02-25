# 기본 설치 프로그램
## 아래 프로그램은 미리 설치해주세요
1. Docker 
2. LocalStack


# 사용하는 포트 numbers 
1. 3306 : mysql
2. 4566 : localstack
3. 8080 : tomcat
4. 6379 : redis  


# DB (실행문 - 신규) 
1. make start (docker를 실행합니다.)
2. make stop (docker를 종료합니다.)  


# localStack 설치
1. localstack CLI 설치합니다.  
   
   ```shell
   brew install localstack/tap/localstack-cli
   ```
   
2. terminal을 열고 아래 CLI를 실행합니다. 

   ```shell
   aws configure --profile localstack
   ```

   🤔 만약에 aws 가 없다고 에러가 난다면, 아래와 같이 aws cli 설치 후 다시 진행해주세요. 

   ```shell
   pip install awscli 로 aws cli
   ```
   
3.  CLI 실행 후 에러가 표시되는데 (AWS Access Key Id 와 AWS Secret Access Key) 2가지만 test라고 입력하고 패스합니다.
   (터미널 표시 예시)  
   
   ```   
   AWS Access Key ID [None]: test  
   AWS Secret Access Key [None]: test  
   Default region name [None]:  
   Default output format [None]:
   ```  


# localStack S3 목록 보기  

```shell
aws --endpoint-url=http://localhost:4566 --profile localstack s3 ls  
```


# Flyway
DB 테이블 형상을 관리하는 툴입니다.

1. DB를 아예 초기화 하고 새롭게 데이터를 쓰고 싶다면
`flywayClean -> flywayMigrate`

2. 새롭게 추가된 migrate만 적용하고싶다면 
`flywayMigrate`  



# Git 정리 명령어

Mac:
```
git fetch --prune && git branch -vv | grep ': gone]'|  awk '{print $1}' | xargs git branch -D
```
Window:
```
git fetch --prune ; git branch -vv | Select-String -Pattern ': gone]' | ForEach-Object { git branch -D $_.ToString().Trim().Split()[0] }
```

