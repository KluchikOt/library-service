# Короткие команды для сборки и запуска проекта.
# Использование: make <команда>, например: make up

.PHONY: build run test up down

# Собрать jar в папку target/ (без тестов)
build:
	./mvnw clean package -DskipTests

# Запустить приложение локально (нужен JDK 25)
run:
	./mvnw spring-boot:run

# Запустить тесты
test:
	./mvnw test

# Собрать Docker-образ и запустить контейнер (остановить: Ctrl+C, затем make down)
up:
	docker compose up --build

# Остановить и удалить контейнер
down:
	docker compose down
