# Saturn Moon - E-Commerce Frontend

Sitio web e-commerce completo para Saturn Moon, negocio local de ropa juvenil urbana desarrollado con HTML5, CSS3 (Bootstrap 5) y JavaScript vanilla.

## Estructura de Archivos

\`\`\`
saturn-moon/
├── index.html              # Página de inicio
├── catalog.html            # Catálogo de productos con filtros
├── cart.html               # Carrito de compras
├── wishlist.html           # Lista de deseos
├── login.html              # Iniciar sesión (cliente/admin)
├── register.html           # Registrarse como cliente
├── profile.html            # Perfil de usuario
├── orders.html             # Historial de pedidos
├── admin-dashboard.html    # Panel administrativo
└── assets/
    ├── css/
    │   └── styles.css      # Estilos personalizados
    └── js/
        └── app.js          # Scripts compartidos
\`\`\`

## Características Principales

### Cliente
- ✅ **Registro e Inicio de Sesión**: Sistema de autenticación diferenciado
- ✅ **Catálogo de Productos**: Buscador y filtros (categoría, precio, talla, color)
- ✅ **Carrito de Compras**: Gestión de cantidad, cálculo de impuestos y envío
- ✅ **Lista de Deseos**: Guardar productos favoritos
- ✅ **Perfil de Usuario**: Gestión de datos personales y dirección
- ✅ **Historial de Pedidos**: Visualización de estado y detalles
- ✅ **Sistema de Cupones**: Códigos descuento (LUNA10, SATURN20)

### Administrador
- ✅ **Dashboard**: KPIs de ventas, pedidos y productos
- ✅ **Gestión de Productos**: CRUD completo (crear, editar, eliminar)
- ✅ **Gestión de Pedidos**: Cambio de estado (pendiente → enviado → entregado)
- ✅ **Reporte de Pedidos**: Filtros por estado y búsqueda

## Tecnologías Utilizadas

- **Frontend**: HTML5, CSS3, JavaScript ES6+
- **Framework CSS**: Bootstrap 5.3
- **Iconos**: FontAwesome 6.4
- **Almacenamiento**: localStorage (navegador)
- **Tema**: Oscuro/Violeta con colores vibrantes para productos

## Guía de Instalación

1. **Descargar archivos**
   \`\`\`bash
   git clone <repositorio>
   cd saturn-moon
   \`\`\`

2. **Servidor local** (cualquier navegador moderno)
   - Abrir `index.html` directamente o usar:
   \`\`\`bash
   python -m http.server 8000
   # Luego visitar: http://localhost:8000
   \`\`\`

## Credenciales de Demo

### Cliente
- Email: cualquier@email.com
- Contraseña: cualquiera

### Administrador
- Email: cualquier@email.com
- Contraseña: **admin123**
- (Se selecciona "Administrador" en el login)

## Estructura de Datos

### Usuario (localStorage)
\`\`\`json
{
  "id": 1234567890,
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@email.com",
  "phone": "+34 600 000 000",
  "role": "customer",
  "address": {
    "street": "Calle Principal 123",
    "city": "Madrid",
    "postalCode": "28001",
    "country": "España"
  },
  "createdAt": "2025-01-15T10:30:00.000Z"
}
\`\`\`

### Producto
\`\`\`json
{
  "id": 1,
  "name": "Camiseta Urban Vibe",
  "category": "camisetas",
  "price": 29.99,
  "image": "/placeholder.svg",
  "sizes": ["XS", "S", "M", "L", "XL", "XXL"],
  "colors": ["Negro", "Blanco"],
  "description": "Camiseta básica con estilo urbano"
}
\`\`\`

### Pedido
\`\`\`json
{
  "id": 1234567890,
  "userId": 1234567890,
  "items": [...],
  "subtotal": 99.99,
  "tax": 21.00,
  "shipping": 5.00,
  "total": 125.99,
  "status": "pendiente",
  "createdAt": "2025-01-15T10:30:00.000Z",
  "shippingAddress": {...}
}
\`\`\`

## Características de Seguridad

- ✅ Validación de formularios en cliente
- ✅ Protección de rutas (redirección a login si no autenticado)
- ✅ Roles diferenciados (cliente/admin)
- ✅ Contraseña hasheada (base64 demo)
- ✅ Gestión de sesiones con localStorage

## Próximos Pasos (Backend)

Para producción, necesitarás implementar:

1. **Backend Spring Boot**
   - API REST endpoints
   - Autenticación JWT
   - Validación y seguridad

2. **Base de Datos MySQL**
   - Tablas: usuarios, productos, pedidos, items_pedidos
   - Relaciones y constraints

3. **Integración**
   - Reemplazar localStorage con llamadas API
   - Validación server-side
   - Pasarela de pagos

## Contacto

Para más información sobre Saturn Moon, contáctanos a través del sitio web.

---

**Desarrollado con** HTML5, CSS3 y JavaScript para Saturn Moon 2025
