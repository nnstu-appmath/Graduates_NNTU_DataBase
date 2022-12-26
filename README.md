# CRUD/веб приложение "База данных выпускников кафедры ПМ"

Требуется для установки:
* Java 8+
* IntelliJ IDEA
* Apache Tomcat 9.0.631

## Запуск приложения на локальном сервере

1. Склонировать проект из github
    ```git clone https://github.com/nnstu-appmath/Graduates_NNTU_DataBase```
2. В Intellij IDEA создать конфигурацию Tomcat и указать путь к папке
3. Запустить конфигурацию
4. Перейти в любом браузере по адресу
    ```http://localhost:8080/GraduatesProject_war_exploded/graduates```
5. Настройка поиска осуществляется через формирование GET запроса
    ```http://localhost:8080/GraduatesProject_war_exploded/graduates?hometown=moscow&degree=bachelor```

## Запуск консольного приложения

1. Склонировать проект из github
    ```git clone https://github.com/nnstu-appmath/Webam```
2. В Intellij IDEA создать конфигурацию в виде консольного приложения
3. В коде класса Main, в методе clientCode() сформировать запрос через статические методы класса GraduatesDAO
4. Запустить конфигурацию
