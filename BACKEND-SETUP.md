# Saturn Moon - Guía de Configuración Backend (Spring Boot + MySQL)

## Requisitos Previos

- Java 17 o superior
- Maven 3.8+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse o VS Code)
- Git

## Configuración del Proyecto Spring Boot

### 1. Crear Proyecto Spring Boot

\`\`\`bash
# Opción 1: Usando Spring Boot CLI
spring boot new saturn-moon-backend --from=spring-boot/getting-started/web

# Opción 2: Usando Maven
mvn archetype:generate \
  -DgroupId=com.saturnmoon \
  -DartifactId=saturn-moon-backend \
  -DarchetypeArtifactId=maven-archetype-quickstart
\`\`\`

### 2. Dependencias pom.xml

\`\`\`xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
\`\`\`

### 3. Configuración application.properties

\`\`\`properties
# Servidor
server.port=8080
server.servlet.context-path=/api

# Base de Datos
spring.datasource.url=jdbc:mysql://localhost:3306/saturn_moon
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# JWT
app.jwt.secret=tu_clave_secreta_muy_larga_y_segura_aqui_minimo_256_bits
app.jwt.expiration=86400000

# CORS
cors.allowed-origins=http://localhost:3000,http://localhost:8000

# Logging
logging.level.root=INFO
logging.level.com.saturnmoon=DEBUG
