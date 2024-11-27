FROM openjdk:17-jdk-slim

# Установка необходимых пакетов, включая curl и другие зависимости
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    --no-install-recommends

# Копирование вашего проекта в контейнер
COPY . /app

WORKDIR /app

# Скачивание и установка Google Chrome
RUN curl -LO https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb && \
    apt-get update && \
    apt-get install -y ./google-chrome-stable_current_amd64.deb && \
    rm google-chrome-stable_current_amd64.deb

# Установка прав на выполнение для Gradle
RUN chmod +x ./gradlew

# Сборка и запуск тестов
RUN ./gradlew clean test









