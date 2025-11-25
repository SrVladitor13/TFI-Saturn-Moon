-- ACTUALIZAR CONTRASEÑA DEL ADMIN
-- Usuario: admin@saturnmoon.com
-- Contraseña: admin123
-- Hash BCrypt de "admin123" (generado con BCryptPasswordEncoder strength 10)
UPDATE users 
SET password = '$2a$10$N9qo8uLOickgx2ZMRZoMye4jY0E6hYe1kqBqLQiT8d3zZQZ3bZqQS'
WHERE email = 'admin@saturnmoon.com';

-- ACTUALIZAR CONTRASEÑA DEL USUARIO DEMO
-- Usuario: juan@email.com  
-- Contraseña: password123
-- Hash BCrypt de "password123" (generado con BCryptPasswordEncoder strength 10)
UPDATE users 
SET password = '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG5SX/6JBR9QPgrqRm'
WHERE email = 'juan@email.com';

-- Verificar que los usuarios fueron actualizados correctamente
SELECT id, email, first_name, last_name, role_id, is_active, created_at 
FROM users 
WHERE email IN ('admin@saturnmoon.com', 'juan@email.com');
