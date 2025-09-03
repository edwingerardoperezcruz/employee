# Employee API

API REST para gestión de empleados, construida con **Spring Boot**, **Spring Data JPA**, **MySQL** y **Docker**.

---

## 🛠 Tecnologías

- Java 17
- Spring Boot 2.7.18
- Spring Data JPA
- MySQL 8
- Docker / Docker Compose
- Lombok
- Springdoc OpenAPI (Swagger UI)

---

## 🚀 Endpoints útiles

### 1️⃣ OpenAPI / Swagger UI

- La documentación interactiva está disponible en:
  http://localhost:8080/swagger-ui

### 2️⃣ Actuator

- Para monitoreo y health checks: http://localhost:8080/actuator/health

### 3️⃣ Postman

- Puedes importar una **colección de Postman** para probar todos los endpoints:
  - GET /employees
  - GET /employees/{id}
  - POST /employees
  - PATCH /employees/{id}
  - DELETE /employees/{id}
  - GET /employees/search?name={name}

---

## 📁 Estructura del proyecto
        employee/
        ├── src/ # Código fuente
        ├── target/ # JAR generado por Maven
        ├── Dockerfile # Para construir la imagen de la app
        ├── docker-compose.yml # Para levantar API + MySQL
        ├── application.properties
        └── pom.xml

---

## ⚙️ Instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/edwingerardoperezcruz/invex-employee
cd invex-employee

mvn clean install

docker-compose up --build
