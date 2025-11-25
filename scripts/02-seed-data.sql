-- INSERTANDO ROLES
INSERT INTO roles (name) VALUES ('CUSTOMER');
INSERT INTO roles (name) VALUES ('ADMIN');

-- INSERTANDO CATEGORÍAS
INSERT INTO categories (name, description) VALUES 
('camisetas', 'Camisetas y tops para hombre y mujer'),
('pantalones', 'Pantalones, shorts y bermudas'),
('hoodies', 'Hoodies y sudaderas deportivas'),
('accesorios', 'Gorras, mochilas y accesorios');

-- INSERTANDO USUARIO ADMIN con contraseña: admin123
INSERT INTO users (first_name, last_name, email, password, phone, role_id, is_active) VALUES 
('Admin', 'Saturn', 'admin@saturnmoon.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye4jY0E6hYe1kqBqLQiT8d3zZQZ3bZqQS', '+34 600 000 001', 2, TRUE);

-- INSERTANDO USUARIO CLIENTE DE DEMO con contraseña: password123
INSERT INTO users (first_name, last_name, email, password, phone, role_id, is_active) VALUES 
('Juan', 'Pérez', 'juan@email.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG5SX/6JBR9QPgrqRm', '+34 600 000 002', 1, TRUE);

-- INSERTANDO PRODUCTOS - CAMISETAS
INSERT INTO products (name, description, price, category_id, stock, image_url) VALUES 
('Camiseta Urban Vibe', 'Camiseta básica con estilo urbano moderno', 29.99, 1, 50, '/images/tshirt1.jpg'),
('Camiseta Gráfica Moon', 'Camiseta con gráfico de luna y diseño minimalista', 34.99, 1, 45, '/images/tshirt2.jpg'),
('Camiseta Oversized Black', 'Camiseta oversized en color negro premium', 39.99, 1, 35, '/images/tshirt3.jpg');

-- INSERTANDO PRODUCTOS - PANTALONES
INSERT INTO products (name, description, price, category_id, stock, image_url) VALUES 
('Pantalón Cargo Urbano', 'Pantalón cargo con bolsillos laterales', 59.99, 2, 30, '/images/cargo1.jpg'),
('Pantalón Slim Fit Negro', 'Pantalón slim fit ajustado y cómodo', 49.99, 2, 40, '/images/slim1.jpg'),
('Shorts Deportivos', 'Shorts cortos para entrenamiento y ocio', 39.99, 2, 50, '/images/shorts1.jpg');

-- INSERTANDO PRODUCTOS - HOODIES
INSERT INTO products (name, description, price, category_id, stock, image_url) VALUES 
('Hoodie Clásico Violeta', 'Sudadera hoodie con capucha en violeta oscuro', 69.99, 3, 25, '/images/hoodie1.jpg'),
('Hoodie Gráfico Tech', 'Hoodie con diseño gráfico futurista', 74.99, 3, 20, '/images/hoodie2.jpg'),
('Zip Hoodie Negro', 'Sudadera con cierre de cremallera completa', 64.99, 3, 28, '/images/hoodie3.jpg');

-- INSERTANDO PRODUCTOS - ACCESORIOS
INSERT INTO products (name, description, price, category_id, stock, image_url) VALUES 
('Gorra Ajustable', 'Gorra de algodón 100% con cierre ajustable', 24.99, 4, 60, '/images/cap1.jpg'),
('Mochila Urban Pack', 'Mochila con compartimentos múltiples', 89.99, 4, 15, '/images/backpack1.jpg'),
('Calcetines Pack x3', 'Pack de 3 pares de calcetines premium', 19.99, 4, 80, '/images/socks1.jpg');

-- INSERTANDO ATRIBUTOS - TALLAS
INSERT INTO product_attributes (product_id, attribute_type, attribute_value) VALUES 
(1, 'SIZE', 'XS'), (1, 'SIZE', 'S'), (1, 'SIZE', 'M'), (1, 'SIZE', 'L'), (1, 'SIZE', 'XL'), (1, 'SIZE', 'XXL'),
(2, 'SIZE', 'S'), (2, 'SIZE', 'M'), (2, 'SIZE', 'L'), (2, 'SIZE', 'XL'),
(3, 'SIZE', 'M'), (3, 'SIZE', 'L'), (3, 'SIZE', 'XL');

-- INSERTANDO ATRIBUTOS - COLORES
INSERT INTO product_attributes (product_id, attribute_type, attribute_value) VALUES 
(1, 'COLOR', 'Negro'), (1, 'COLOR', 'Blanco'), (1, 'COLOR', 'Gris'),
(2, 'COLOR', 'Negro'), (2, 'COLOR', 'Violeta'),
(4, 'COLOR', 'Negro'), (4, 'COLOR', 'Azul'), (4, 'COLOR', 'Gris Oscuro');

-- INSERTANDO CUPONES
INSERT INTO coupons (code, discount_type, discount_value, max_uses, is_active) VALUES 
('LUNA10', 'PERCENTAGE', 10, 1000, TRUE),
('SATURN20', 'PERCENTAGE', 20, 500, TRUE),
('WELCOME5', 'FIXED', 5, NULL, TRUE);

-- INSERTANDO DIRECCIÓN DE USUARIO
INSERT INTO user_addresses (user_id, street, city, postal_code, country, is_default) VALUES 
(2, 'Calle Mayor 123', 'Madrid', '28001', 'España', TRUE);
