# 🏥 Clinical Management System (Spring Boot + MySQL)

Sistema integral de gestión clínica con módulos para médicos, enfermería, administración y soporte.  
Desarrollado en **Java 17**, **Spring Boot 3.5.4**, **MySQL**, y con interfaz **Swing (JOptionPane)**.

---
## Integrantes
SebastianMontoya
-Juan Tabares
-Diego Vazquez
-Luis Martinez

Grupo Lunes - Miércoles 8:00pm - 10:00pm

## ⚙️ Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

| Requisito | Versión recomendada | Descripción |
|------------|--------------------|--------------|
| ☕ Java | 17 o superior | SDK necesario para compilar y ejecutar el proyecto |
| 🧱 Maven | 3.9+ | Gestor de dependencias |
| 🐬 MySQL | 8.0+ | Base de datos relacional |
| 🧰 XAMPP | Última versión | Para levantar Apache y MySQL fácilmente |
| 💻 IDE | NetBeans / IntelliJ / VS Code | Para editar y correr el proyecto |

---
## 🗄️ Base de datos

Crear la BD 

CREATE DATABASE clinica_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

Configurar el archivo application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/clinica_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

## 🚀 Ejecución del proyecto

Desde la consola mvn spring-boot:run


## 🧩 Dependencias principales

El proyecto ya incluye todas las dependencias necesarias en `pom.xml`.  
Las más importantes son:

```xml
<dependencies>
    <!-- Framework principal -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Conexión MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>

    <!-- Validaciones -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Lombok (para reducir boilerplate de getters/setters) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Swing (Interfaz gráfica con JOptionPane) -->
    <dependency>
        <groupId>javax.swing</groupId>
        <artifactId>swing</artifactId>
        <version>1.0</version>
        <scope>system</scope>
        <systemPath>${java.home}/lib/ext/swing.jar</systemPath>
    </dependency>
</dependencies>


  

- Spring Boot
- MySQL
