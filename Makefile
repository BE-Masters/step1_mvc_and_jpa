start:
	cd infra && docker-compose up -d && \
    aws --endpoint-url=http://localhost:4566 --profile localstack s3api create-bucket --bucket policy-pdf-bucket

stop:
	docker stop $$(docker ps -q)
