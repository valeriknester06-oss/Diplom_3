# Diplom_3

Автотесты UI для веб-приложения Stellar Burgers.

## Используемые технологии

* Java 11
* Selenium WebDriver
* JUnit 4
* Maven
* Allure Report

## Запуск тестов

### Google Chrome

mvn clean test

### Yandex Browser

mvn clean test -Dbrowser=yandex

## Генерация Allure-отчета

mvn allure:serve

## Структура проекта

* pages — Page Object классы
* tests — тестовые классы
* utils — базовые настройки и генерация данных
