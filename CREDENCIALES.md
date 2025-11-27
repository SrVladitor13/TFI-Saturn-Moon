# Credenciales de Acceso - Saturn Moon

## Cuenta Administrador

**Email:** admin@saturnmoon.com  
**Contraseña:** admin123

## Usuario de Prueba (Cliente)

**Email:** juan@email.com  
**Contraseña:** password123

## Notas Importantes

- Las contraseñas están encriptadas con BCrypt en la base de datos
- Para crear nuevos usuarios, usa el formulario de registro en http://localhost:8080/register.html
- Los nuevos usuarios tienen rol "CUSTOMER" por defecto
- Solo el administrador puede acceder al panel de administración

## Base de Datos

**Base de datos:** saturn_moon  
**Usuario MySQL:** root  
**Contraseña MySQL:** (configurada en application.properties)

## Ejecutar Scripts SQL

Ejecuta los scripts en este orden:

1. `01-create-schema.sql` - Crea todas las tablas
2. `02-seed-data.sql` - Inserta datos iniciales (roles, categorías, productos, usuarios)

Los scripts se pueden ejecutar desde MySQL Workbench o desde la línea de comandos:

\`\`\`bash
mysql -u root -p saturn_moon < scripts/01-create-schema.sql
mysql -u root -p saturn_moon < scripts/02-seed-data.sql
\`\`\`

## URLs Importantes

- **Frontend:** http://localhost:8080/
- **Login:** http://localhost:8080/login.html
- **Registro:** http://localhost:8080/register.html
- **Catálogo:** http://localhost:8080/catalog.html
- **Panel Admin:** http://localhost:8080/admin-dashboard.html

## API REST Endpoints

- **Login:** POST http://localhost:8080/api/auth/login
- **Register:** POST http://localhost:8080/api/auth/register
- **Productos:** GET http://localhost:8080/api/products
- **Categorías:** GET http://localhost:8080/api/categories

## Solución de Problemas

### Los usuarios no se guardan en la base de datos

1. Verifica que la base de datos `saturn_moon` existe
2. Ejecuta los scripts SQL en orden
3. Verifica que el rol "CUSTOMER" existe en la tabla `roles`
4. Revisa los logs de Spring Boot para errores de transacción

### No puedo iniciar sesión con admin

1. Asegúrate de haber ejecutado el script `02-seed-data.sql`
2. La contraseña es exactamente: admin123 (sin espacios)
3. El email es: admin@saturnmoon.com

### Error de conexión a la base de datos

Verifica en `application.properties`:
- `spring.datasource.url=jdbc:mysql://localhost:3306/saturn_moon`
- `spring.datasource.username=root`
- `spring.datasource.password=tu_password_mysql`
