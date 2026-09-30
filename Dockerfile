# ===== Этап 1: сборка jar =====
# Образ с полным JDK 25: для сборки нужен компилятор
FROM eclipse-temurin:25-jdk AS build

# Рабочая папка внутри образа
WORKDIR /app

# Сначала копируем только файлы Maven. Пока pom.xml не меняется,
# Docker берёт зависимости из кэша и не скачивает их заново
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Убираем переводы строк Windows и даём право на запуск,
# иначе ./mvnw может не запуститься внутри Linux-контейнера
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

# Скачиваем зависимости отдельным шагом (кэшируется)
RUN ./mvnw dependency:go-offline -B

# Копируем исходники и собираем jar. Тесты запускаются отдельно: make test
COPY src src
RUN ./mvnw package -DskipTests -B

# ===== Этап 2: запуск =====
# Образ только с JRE: для запуска компилятор не нужен, образ легче
FROM eclipse-temurin:25-jre

WORKDIR /app

# Берём готовый jar из первого этапа. Maven и исходники в итоговый образ не попадают
COPY --from=build /app/target/*.jar app.jar

# Порт, который слушает Spring Boot
EXPOSE 8080

# Команда, которая выполняется при старте контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
