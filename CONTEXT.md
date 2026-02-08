# 🍟 El Garage del Antojo - Contexto del Proyecto

## 🎯 Objetivo del Proyecto

**El Garage del Antojo** es una aplicación móvil de pedidos en línea para un negocio de comida que se especializa en:
- 🍟 **Papas a la francesa** en diferentes estilos (clásicas, gajo, rizadas, etc.)
- 🍹 **Preparados para micheladas** (salsas, botaneros, complementos)
- 🍴 Otros productos del menú

## 📱 Plataforma

- **Inicial**: Android (Google Play Store)
- **Futuro**: iOS (usando Kotlin Multiplatform)

## 👥 Usuarios Objetivo

- Clientes que desean ordenar comida rápidamente
- Usuarios que buscan personalizar sus pedidos
- Personas que prefieren recoger o recibir delivery

## 🎨 Funcionalidades Principales

### Fase 1 - MVP (Mínimo Producto Viable)
- [ ] **Autenticación**: Login con Google, Email/Password
- [ ] **Menú de Productos**: Visualizar productos por categorías
- [ ] **Carrito de Compras**: Agregar/quitar productos
- [ ] **Personalización**: Opciones de personalización por producto
- [ ] **Checkout**: Confirmar pedido
- [ ] **Historial de Pedidos**: Ver pedidos anteriores
- [ ] **Perfil de Usuario**: Gestionar datos personales

### Fase 2 - Mejoras
- [ ] **Pagos en Línea**: Integración con pasarelas de pago
- [ ] **Tracking en Tiempo Real**: Seguimiento del pedido
- [ ] **Notificaciones Push**: Actualizaciones del pedido
- [ ] **Favoritos**: Guardar productos favoritos
- [ ] **Promociones**: Sistema de cupones y descuentos
- [ ] **Calificaciones**: Review de productos

### Fase 3 - Avanzado
- [ ] **Programa de Lealtad**: Puntos y recompensas
- [ ] **Delivery Map**: Mapa de seguimiento
- [ ] **Chat con Soporte**: Comunicación en vivo
- [ ] **Pedidos Recurrentes**: Guardar pedidos frecuentes

## 🏗️ Stack Tecnológico

### Frontend
- **Kotlin Multiplatform (KMP)**: Código compartido
- **Jetpack Compose**: UI moderna y declarativa
- **Coroutines & Flow**: Programación asíncrona
- **Koin**: Inyección de dependencias

### Backend
- **Firebase Authentication**: Autenticación de usuarios
- **Firebase Firestore**: Base de datos NoSQL en tiempo real
- **Firebase Storage**: Almacenamiento de imágenes
- **Firebase Cloud Messaging**: Notificaciones push
- **Firebase Functions**: Lógica del lado del servidor (futuro)

### Arquitectura
- **Clean Architecture**: Separación de capas
- **SOLID Principles**: Código mantenible
- **Repository Pattern**: Abstracción de datos
- **Use Cases**: Lógica de negocio encapsulada

### CI/CD
- **GitHub Actions**: Automatización de builds y tests
- **Ktlint**: Code style y linting
- **JUnit + MockK**: Testing unitario

## 📊 Modelos de Datos Principales

### User
```kotlin
data class User(
    val id: String,
    val email: String,
    val displayName: String,
    val phoneNumber: String?,
    val photoUrl: String?,
    val createdAt: Timestamp
)
```

### Product
```kotlin
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: Category,
    val imageUrl: String,
    val customizations: List<Customization> = emptyList(),
    val available: Boolean = true
)
```

### Order
```kotlin
data class Order(
    val id: String,
    val userId: String,
    val items: List<OrderItem>,
    val total: Double,
    val status: OrderStatus,
    val createdAt: Timestamp,
    val deliveryAddress: String?,
    val notes: String?
)
```

### Category
```kotlin
enum class Category {
    PAPAS_FRANCESAS,
    PREPARADOS_MICHELADA,
    BEBIDAS,
    EXTRAS,
    SALSAS
}
```

### OrderStatus
```kotlin
enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    READY,
    DELIVERED,
    CANCELLED
}
```

## 🎨 Diseño UI/UX

### Colores Principales (Por definir)
- Primary: [Por definir según branding]
- Secondary: [Por definir según branding]
- Accent: [Por definir según branding]

### Navegación
```
Splash Screen
    ↓
Login/Register
    ↓
Home (Menú Principal)
    ├── Categorías
    ├── Búsqueda
    └── Perfil
        ↓
Detalle de Producto
    ↓
Carrito
    ↓
Checkout
    ↓
Confirmación
```

## 📅 Cronograma Estimado

### Sprint 1 (Semana 1-2)
- Configuración del proyecto ✅
- Estructura de arquitectura ✅
- Modelos de dominio
- Configuración de Firebase

### Sprint 2 (Semana 3-4)
- Sistema de autenticación
- UI básica (Home, Login)
- Navegación

### Sprint 3 (Semana 5-6)
- Menú de productos
- Detalle de producto
- Carrito de compras

### Sprint 4 (Semana 7-8)
- Checkout y creación de pedidos
- Historial de pedidos
- Perfil de usuario

### Sprint 5 (Semana 9-10)
- Testing completo
- Refinamiento de UI
- Preparación para Play Store

## 🚀 Requisitos para Play Store

- [x] Configuración de gradle ✅
- [x] AndroidManifest configurado ✅
- [ ] Íconos de launcher (todos los tamaños)
- [ ] Screenshots (mínimo 2)
- [ ] Feature graphic
- [ ] Descripción de la app
- [ ] Política de privacidad
- [ ] APK firmado (release)
- [ ] Configurar versioning
- [ ] Permisos justificados
- [ ] Testing en múltiples dispositivos

## 🔐 Seguridad y Privacidad

- Autenticación segura con Firebase Auth
- Datos sensibles nunca en código
- Uso de secrets.properties para claves
- HTTPS para todas las comunicaciones
- Validación de datos en servidor (Firebase Rules)

## 📈 Métricas de Éxito

- Tiempo promedio de pedido < 3 minutos
- Tasa de abandono de carrito < 30%
- Calificación en Play Store > 4.0
- 0 crashes críticos en producción
- Tiempo de carga inicial < 2 segundos

## 📝 Notas Adicionales

- El proyecto está preparado para escalar a iOS
- Usar Firebase Emulator para desarrollo local
- Mantener cobertura de tests > 70%
- Documentar cambios importantes
- Seguir Git Flow establecido

---

**Última actualización**: 8 de febrero de 2026
**Estado del proyecto**: En desarrollo inicial
