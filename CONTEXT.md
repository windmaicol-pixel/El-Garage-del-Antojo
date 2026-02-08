# 🍟 El Garage del Antojo - Contexto del Proyecto

## 🎯 Objetivo del Proyecto

**El Garage del Antojo** es una aplicación móvil de pedidos en línea para un negocio de comida local que se especializa en:
- 🍟 **Papas a la francesa** en diferentes estilos (clásicas, gajo, con queso, etc.)
- 🍹 **Preparados para micheladas/clamatos** (mezclas listas para bebidas)
- 📦 **Combos** (paquetes de papas + preparados)

## 📍 Zona de Operación

- **Ubicación**: Fraccionamiento Puerta Navarra, Querétaro, Querétaro
- **Código Postal**: 76116
- **Cobertura**: 10-12 condominios del fraccionamiento
- **Radio**: ~1-1.5 km (vecindario cercano)
- **Ventaja**: Conocimiento total de la zona, entregas rápidas

## 📱 Plataforma

- **Inicial**: Android (Google Play Store)
- **Futuro**: iOS (usando Kotlin Multiplatform cuando tengamos Mac)

## 👥 Usuarios Objetivo

- Residentes del Fraccionamiento Puerta Navarra
- Familias que buscan botanear/preparar micheladas
- Clientes que valoran conveniencia y rapidez
- Personas que prefieren pago al recibir (efectivo/transferencia/tarjeta)

## 🚀 Estrategia de Adquisición

### **Flujo del Negocio:**

```
Publicación en WhatsApp (Grupo del Fracc.)
    ↓
┌────────────────────────────────────┐
│ 📸 Imagen del Menú + Precios       │
│ 🍟 Papas | 🍹 Preparados | 📦 Combos│
│                                    │
│ 🎉 ENVÍO GRATIS TODO EL FRACC 🎉   │
│                                    │
│ 📞 Pide por WhatsApp: XXX-XXX-XXXX │
│ 📲 O descarga la app: [QR Code]   │
│    ↓ Más rápido y sin errores ↓   │
└────────────────────────────────────┘
    ↓
Usuario decide:

OPCIÓN A (Tradicional):
WhatsApp → Mensaje → Admin anota manualmente

OPCIÓN B (Moderna - incentivada):
Escanea QR → Descarga app → Registro → Pedido
```

### **Ventaja Competitiva:**
- ✅ **Envío GRATIS** (incluido en precio) vs competencia ($30-$45)
- ✅ **Entrega rápida**: 15-25 min vs 40-60 min
- ✅ **Local**: Conocemos cada condominio
- ✅ **Sin mínimo** de compra
- ✅ **Pago al recibir** (sin cobro en línea)

## 🎨 Funcionalidades Principales

### **App Cliente:**

#### Fase 1 - MVP (Mínimo Producto Viable)
- [ ] **Autenticación**: Login con Email/Password + Google Sign-In
- [ ] **Registro Completo**: Con dirección detallada (condominio obligatorio)
- [ ] **Home**: Dashboard personalizado con último pedido y productos populares
- [ ] **Menú de Productos**: 3 categorías (Papas, Preparados, Combos)
- [ ] **Carrito de Compras**: Agregar/quitar productos con contador en tiempo real
- [ ] **Checkout**: Desglose completo con 3 métodos de pago
- [ ] **Historial de Pedidos**: Ver pedidos anteriores con opción "Repetir"
- [ ] **Perfil de Usuario**: Gestionar datos personales y dirección

### **Panel de Administrador:**

#### Fase 1 - MVP
- [ ] **Dashboard de Pedidos**: Ver pedidos en tiempo real
- [ ] **Crear Pedido Manual**: Para pedidos por WhatsApp/Llamada
- [ ] **Gestión de Estados**: Cambiar status (Pendiente → Preparando → En Camino → Entregado)
- [ ] **Notificaciones Push**: Alerta de nuevos pedidos
- [ ] **Búsqueda de Clientes**: Para crear pedidos manuales

### Fase 2 - Mejoras
- [ ] **Estadísticas Admin**: Dashboard con ventas, productos más vendidos
- [ ] **Gestión de Menú**: CRUD de productos desde admin
- [ ] **Gestión de Condominios**: Activar/desactivar zonas
- [ ] **Notificaciones de Estado**: Cliente recibe updates del pedido
- [ ] **Control de Inventario**: Marcar productos como disponible/agotado
- [ ] **Favoritos**: Guardar productos favoritos
- [ ] **Promociones**: Códigos de descuento

### Fase 3 - Avanzado
- [ ] **Stock Inteligente**: Control automático de inventario
- [ ] **Programa de Lealtad**: Puntos y recompensas
- [ ] **Chat con Soporte**: WhatsApp integrado
- [ ] **Pedidos Recurrentes**: Guardar combinaciones favoritas
- [ ] **Versión iOS**: Cuando tengamos Mac

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
    val nombre: String,
    val telefono: String,
    val email: String,
    val direccion: Direccion,
    val role: UserRole = UserRole.CLIENTE,
    val createdAt: Timestamp,
    val ultimoPedido: String? = null
)

enum class UserRole {
    CLIENTE,
    ADMIN,
    REPARTIDOR  // Futuro
}
```

### Direccion (Obligatoria y completa)
```kotlin
data class Direccion(
    val condominio: String,          // Dropdown con 10-12 condominios
    val calle: String,                // Prellenada según condominio
    val numeroExterior: String,
    val numeroInterior: String?,      // Depto/Int (opcional)
    val codigoPostal: String = "76116", // Fijo
    val colonia: String = "Puerta Navarra", // Fijo
    val municipio: String = "Querétaro", // Fijo
    val estado: String = "Querétaro", // Fijo
    val referencias: String?          // Opcional
)
```

### Product
```kotlin
data class Product(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,               // Incluye envío
    val categoria: Categoria,
    val imageUrl: String,
    val disponible: Boolean,
    val opciones: List<Opcion>?       // Personalizaciones
)

enum class Categoria {
    PAPAS,
    PREPARADOS,
    COMBOS
}
```

### Order
```kotlin
data class Order(
    val id: String,
    val folio: String,                // #001234
    val userId: String,
    val userName: String,
    val telefono: String,
    val direccion: Direccion,
    val items: List<OrderItem>,
    val total: Double,                // Sin cargo de envío separado
    val metodoPago: MetodoPago,
    val detallesPago: DetallesPago?,
    val status: OrderStatus,
    val origen: OrigenPedido,
    val creadoPor: String?,           // userId del admin si es manual
    val createdAt: Timestamp,
    val confirmedAt: Timestamp?,
    val deliveredAt: Timestamp?,
    val notas: String?
)

enum class MetodoPago {
    EFECTIVO,
    TRANSFERENCIA,
    TARJETA
}

data class DetallesPago(
    val necesitaCambio: Boolean = false,
    val pagaraCon: Double? = null,
    val cambio: Double? = null,
    val comprobanteUrl: String? = null
)

enum class OrderStatus {
    PENDIENTE,
    CONFIRMADO,
    PREPARANDO,
    EN_CAMINO,
    ENTREGADO,
    CANCELADO
}

enum class OrigenPedido {
    APP,          // Cliente desde app
    WHATSAPP,     // Admin creó desde WhatsApp
    LLAMADA       // Admin creó desde llamada
}
```

### Category
```kotlin
enum class Category {
    PAPAS_FRANCESAS,
    PREPARADOS_MICHELADA,
    COMBOS
}
```

## 🎨 Diseño UI/UX

### **Navegación Principal (App Cliente):**
```
Bottom Navigation (4 tabs):
┌─────┬──────┬──────┬────────┐
│ 🏠  │  🍟  │  🛒  │   👤   │
│Home │ Menú │ Cart │ Perfil │
└─────┴──────┴──────┴────────┘

Home: Dashboard personalizado + último pedido
Menú: Productos por categoría (tabs internos)
Cart: Carrito con checkout
Perfil: Datos personales + historial
```

### **Navegación Admin:**
```
┌──────────┬──────────┬──────────┬──────────┐
│    📋    │    🛍️    │    📊    │    ⚙️    │
│ Pedidos  │  Crear   │  Stats   │  Config  │
└──────────┴──────────┴──────────┴──────────┘
```

### Colores (Por definir)
- Primary: Naranja/Amarillo (papas) 🍟
- Secondary: Verde/Lime (micheladas) 🍹
- Accent: Rojo (urgencia/pedidos)
- Background: Blanco/Gris claro

### Flujo de Navegación
```
Splash Screen (1 seg)
    ↓
¿Usuario logueado?
    │
    ├─ NO → Login/Registro
    │         ↓
    └─ SÍ → Home (Dashboard)
              ↓
         Bottom Nav: Home | Menú | Carrito | Perfil
```

## 📅 Cronograma Estimado

### Sprint 1 (Semana 1-2) - Fundamentos + Admin Base
- [x] Configuración del proyecto ✅
- [x] Estructura de arquitectura ✅
- [ ] Firebase proyecto real (Authentication, Firestore, Storage)
- [ ] Modelos de dominio implementados
- [ ] Autenticación con roles (admin/cliente)
- [ ] Dashboard admin básico
- [ ] Pantalla "Crear Pedido Manual"

### Sprint 2 (Semana 3-4) - Autenticación y Registro Cliente
- [ ] UI Login/Registro cliente (Compose)
- [ ] Validación de dirección completa
- [ ] Lista de condominios (dropdown)
- [ ] Home con dashboard personalizado
- [ ] Persistencia de sesión
- [ ] Detección de rol (admin vs cliente)

### Sprint 3 (Semana 5-6) - Menú y Productos
- [ ] Cargar productos desde Firestore
- [ ] UI de categorías (Papas/Preparados/Combos)
- [ ] Lista de productos con imágenes
- [ ] Detalle de producto
- [ ] Agregar al carrito
- [ ] Admin: CRUD de productos

### Sprint 4 (Semana 7-8) - Checkout y Pedidos en Tiempo Real
- [ ] UI del carrito
- [ ] Pantalla de checkout con desglose
- [ ] 3 métodos de pago (efectivo/transferencia/tarjeta)
- [ ] Crear orden en Firestore
- [ ] Notificaciones push en tiempo real (admin)
- [ ] Admin: Cambiar estados de pedidos
- [ ] Pantalla de confirmación

### Sprint 5 (Semana 9-10) - Estadísticas y Lanzamiento
- [ ] Historial de pedidos (usuario)
- [ ] Botón "Repetir pedido"
- [ ] Admin: Estadísticas y reportes
- [ ] Admin: Gestión de condominios
- [ ] Control de inventario (disponible/agotado)
- [ ] Testing completo (unitarios + integración)
- [ ] **Lanzamiento Play Store** 🚀

## 🚀 Requisitos para Play Store

- [x] Configuración de gradle ✅
- [x] AndroidManifest configurado ✅
- [x] Íconos de launcher (todos los tamaños) ✅
- [ ] Screenshots (mínimo 2, ideal 8)
- [ ] Feature graphic (1024x500)
- [ ] Descripción de la app
- [ ] Política de privacidad (URL pública)
- [ ] APK firmado con keystore
- [ ] Configurar versioning (versionCode, versionName)
- [ ] Permisos justificados
- [ ] Testing en múltiples dispositivos
- [ ] Categoría: Food & Drink

## 🔐 Seguridad y Privacidad

- Autenticación segura con Firebase Auth
- Datos sensibles nunca en código
- Uso de secrets.properties para claves
- HTTPS para todas las comunicaciones
- Firebase Security Rules para validación en servidor
- google-services.json en .gitignore

## 💰 Modelo de Negocio

### **Estrategia de Precios:**
- ✅ **Envío GRATIS** (incluido en precio del producto)
- ✅ Precios competitivos vs apps de delivery ($30-$45 envío)
- ✅ Sin pedido mínimo
- ✅ Pago al recibir (sin procesamiento en línea)

### **Ventaja Competitiva:**
- Local del fraccionamiento (conocemos la zona)
- Entregas rápidas (15-25 min vs 40-60 min)
- Sin cargo de envío
- Atención personalizada

### **Proyección Conservadora:**
```
10-12 condominios x 20 viviendas = ~240 viviendas
20% adopción = 48 clientes activos
2 pedidos/semana = 96 pedidos/semana
Ticket promedio: $150

Ingresos mensuales: ~$57,600
Costos (45%): $25,920
Utilidad neta: ~$31,680/mes
```

## 📈 Métricas de Éxito

- Tiempo promedio de pedido < 2 minutos
- Tasa de conversión (descarga → primer pedido) > 40%
- Calificación en Play Store > 4.5
- 0 crashes críticos en producción
- Tiempo de carga inicial < 2 segundos
- Adopción: 20% del fraccionamiento en 3 meses

## 📝 Notas Adicionales

- El proyecto está preparado para escalar a iOS cuando tengamos Mac
- Usar Firebase Emulator para desarrollo local
- Mantener cobertura de tests > 70%
- Documentar cambios importantes
- Seguir Git Flow establecido
- WhatsApp como canal complementario (no reemplaza la app)
- Admin puede crear pedidos manualmente para clientes que pidan por WhatsApp
- Control de inventario simple: disponible/agotado (sin stock numérico por ahora)

## 🎯 Decisiones Importantes

1. **Envío**: GRATIS (incluido en precio) - No cargo separado
2. **Zona**: Solo Fraccionamiento Puerta Navarra (10-12 condominios)
3. **Pago**: Al recibir (efectivo/transferencia/tarjeta) - Sin cobro en línea
4. **Plataforma inicial**: Solo Android (iOS cuando tengamos Mac)
5. **Marketing**: WhatsApp + QR Code + "Envío Gratis"
6. **Roles**: Cliente y Admin en la misma app

---

**Última actualización**: 8 de febrero de 2026  
**Estado del proyecto**: Base configurada, listo para desarrollo  
**Ubicación**: Puerta Navarra, Querétaro, QRO 76116
