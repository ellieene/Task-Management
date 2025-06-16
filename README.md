# Task-Management


```
    Регистрация проходит через JWT токен
```

### 2. Запуск приложения

#### Локально
```bash
sh setup-database.sh
mvn clean package
mvn spring-boot:run
```

#### В Docker

```bash
mvn clean package
docker-compose up --build
```


### Версии
```
 OpenJDK v.17  
 Maven v.3.9.9
```


/v3/api-docs  - в поиске swagger<br>
#### Для докера
[Открыть Swagger UI](http://localhost:8081/swagger-ui/index.html)

#### Локальный swagger
[Открыть Swagger UI](http://localhost:8080/swagger-ui/index.html)