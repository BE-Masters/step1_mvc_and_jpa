start:
	cd infra && docker-compose up -d

stop:
	docker stop $$(docker ps -q)
