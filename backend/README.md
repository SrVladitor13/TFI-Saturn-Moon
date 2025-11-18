# Saturn Moon Backend - Spring Boot

API REST para el sistema e-commerce Saturn Moon desarrollado con Spring Boot, MySQL y Spring Security con JWT.

## Requisitos Previos

- Java 17 o superior
- Maven 3.8+
- MySQL 8.0+
- IDE (IntelliJ IDEA recomendado)

## Configuración

### 1. Clonar el repositorio

\`\`\`bash
cd backend
\`\`\`

### 2. Configurar la base de datos

Ejecutar el script SQL para crear la base de datos:

\`\`\`bash
mysql -u root -p < scripts/01-create-schema.sql
mysql -u root -p saturn_moon < scripts/02-seed-data.sql
\`\`\`

### 3. Configurar application.properties

Editar `src/main/resources/application.properties` con tus credenciales de MySQL:

\`\`\`properties
spring.datasource.url=jdbc:mysql://localhost:3306/saturn_moon
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
\`\`\`

### 4. Compilar el proyecto

\`\`\`bash
mvn clean install
\`\`\`

### 5. Ejecutar la aplicación

\`\`\`bash
mvn spring-boot:run
\`\`\`

O desde tu IDE, ejecutar la clase `SaturnMoonApplication.java`

La API estará disponible en: `http://localhost:8080/api`

## Endpoints Principales

### Autenticación
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Iniciar sesión

### Productos
- `GET /api/products` - Listar todos los productos
- `GET /api/products/{id}` - Obtener producto por ID
- `GET /api/products/category/{categoryId}` - Productos por categoría
- `GET /api/products/search?keyword=` - Buscar productos
- `POST /api/products` - Crear producto (Admin)
- `PUT /api/products/{id}` - Actualizar producto (Admin)
- `DELETE /api/products/{id}` - Eliminar producto (Admin)

### Carrito
- `GET /api/cart` - Ver carrito
- `POST /api/cart/add` - Añadir al carrito
- `PUT /api/cart/item/{itemId}` - Actualizar cantidad
- `DELETE /api/cart/item/{itemId}` - Eliminar del carrito

### Pedidos
- `POST /api/orders` - Crear pedido
- `GET /api/orders` - Ver mis pedidos
- `GET /api/orders/{id}` - Ver detalle de pedido
- `GET /api/orders/admin/all` - Ver todos los pedidos (Admin)
- `PUT /api/orders/{id}/status` - Actualizar estado (Admin)

### Lista de Deseos
- `GET /api/wishlist` - Ver lista de deseos
- `POST /api/wishlist/{productId}` - Añadir a lista
- `DELETE /api/wishlist/{productId}` - Eliminar de lista

### Categorías
- `GET /api/categories` - Listar categorías

## Estructura del Proyecto

\`\`\`
backend/
├── src/main/java/com/saturnmoon/
│   ├── config/              # Configuraciones (Security, CORS)
│   ├── controller/          # Controladores REST
│   ├── dto/                 # Data Transfer Objects
│   ├── model/               # Entidades JPA
│   ├── repository/          # Repositorios Spring Data
│   ├── security/            # Seguridad JWT
│   ├── service/             # Lógica de negocio
│   └── SaturnMoonApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
\`\`\`

## Usuarios de Prueba

Después de ejecutar el seed:

**Admin:**
- Email: admin@saturnmoon.com
- Password: admin123

**Cliente:**
- Email: juan@email.com
- Password: cliente123

## Tecnologías Utilizadas

- Spring Boot 3.2.0
- Spring Security + JWT
- Spring Data JPA
- MySQL 8.0
- Lombok
- Maven

## Notas

- La autenticación usa JWT tokens en el header: `Authorization: Bearer {token}`
- Los endpoints protegidos requieren autenticación
- Los endpoints de administración requieren rol ADMIN
\`\`\`
