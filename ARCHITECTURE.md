# 🏗️ Arquitectura - El Garage del Antojo

## Arquitectura Clean + SOLID

Este proyecto implementa **Clean Architecture** con principios **SOLID** para garantizar:
- ✅ Código mantenible y escalable
- ✅ Fácil testing
- ✅ Bajo acoplamiento
- ✅ Alta cohesión
- ✅ Independencia de frameworks

---

## 📂 Estructura del Proyecto

```
shared/                          # Módulo compartido KMP
├── commonMain/kotlin/
│   ├── domain/                  # Capa de dominio (lógica de negocio)
│   │   ├── model/              # Entidades del dominio
│   │   │   ├── User.kt
│   │   │   ├── Product.kt
│   │   │   ├── Order.kt
│   │   │   └── Category.kt
│   │   ├── repository/         # Interfaces (ISP - Interface Segregation)
│   │   │   ├── IAuthRepository.kt
│   │   │   ├── IProductRepository.kt
│   │   │   ├── IOrderRepository.kt
│   │   │   └── IUserRepository.kt
│   │   └── usecase/            # Casos de uso (SRP - Single Responsibility)
│   │       ├── auth/
│   │       │   ├── SignInUseCase.kt
│   │       │   ├── SignOutUseCase.kt
│   │       │   └── GetCurrentUserUseCase.kt
│   │       ├── product/
│   │       │   ├── GetProductsUseCase.kt
│   │       │   ├── GetProductByCategoryUseCase.kt
│   │       │   └── SearchProductsUseCase.kt
│   │       └── order/
│   │           ├── CreateOrderUseCase.kt
│   │           ├── GetOrderHistoryUseCase.kt
│   │           └── CancelOrderUseCase.kt
│   ├── data/                   # Capa de datos (implementaciones)
│   │   ├── repository/
│   │   │   ├── AuthRepositoryImpl.kt
│   │   │   ├── ProductRepositoryImpl.kt
│   │   │   ├── OrderRepositoryImpl.kt
│   │   │   └── UserRepositoryImpl.kt
│   │   ├── datasource/
│   │   │   ├── remote/        # Firebase, APIs
│   │   │   └── local/         # Cache, DB local
│   │   └── dto/               # Data Transfer Objects
│   └── di/                    # Inyección de dependencias (Koin)
│       ├── DomainModule.kt
│       ├── DataModule.kt
│       └── AppModule.kt
├── commonTest/kotlin/
│   └── domain/usecase/        # Tests unitarios
│       ├── auth/
│       ├── product/
│       └── order/
├── androidMain/kotlin/        # Código específico de Android
└── iosMain/kotlin/            # Código específico de iOS

androidApp/                     # Aplicación Android
├── src/main/java/
│   ├── ui/                    # Capa de presentación (Compose)
│   │   ├── navigation/
│   │   ├── screens/
│   │   │   ├── home/
│   │   │   ├── menu/
│   │   │   ├── cart/
│   │   │   ├── orders/
│   │   │   └── profile/
│   │   └── components/
│   ├── viewmodel/            # ViewModels
│   └── theme/                # Theming
└── src/test/java/            # Tests Android
```

---

## 🎯 Principios SOLID Aplicados

### **S - Single Responsibility Principle**
Cada clase tiene una única responsabilidad:
```kotlin
// ✅ CORRECTO
class GetProductsUseCase(private val repository: IProductRepository) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}

// ❌ INCORRECTO - hace múltiples cosas
class ProductManager {
    fun getProducts() {}
    fun createOrder() {}
    fun sendNotification() {}
}
```

### **O - Open/Closed Principle**
Abierto para extensión, cerrado para modificación:
```kotlin
// ✅ CORRECTO - extensible mediante interfaces
interface IPaymentProvider {
    suspend fun processPayment(amount: Double): Result<Payment>
}

class StripePaymentProvider : IPaymentProvider { /* ... */ }
class PayPalPaymentProvider : IPaymentProvider { /* ... */ }
```

### **L - Liskov Substitution Principle**
Los objetos derivados deben ser sustituibles por sus tipos base:
```kotlin
// ✅ CORRECTO - cualquier implementación funciona
class OrderService(private val paymentProvider: IPaymentProvider) {
    suspend fun checkout(order: Order) {
        paymentProvider.processPayment(order.total)
    }
}
```

### **I - Interface Segregation Principle**
Interfaces específicas mejor que una general:
```kotlin
// ✅ CORRECTO - interfaces segregadas
interface IAuthRepository {
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signOut(): Result<Unit>
}

interface IProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProductById(id: String): Result<Product>
}

// ❌ INCORRECTO - interfaz demasiado grande
interface IRepository {
    fun signIn() {}
    fun getProducts() {}
    fun createOrder() {}
    fun updateUser() {}
    // ... muchos más métodos
}
```

### **D - Dependency Inversion Principle**
Depender de abstracciones, no de implementaciones:
```kotlin
// ✅ CORRECTO - depende de abstracción
class GetProductsUseCase(
    private val repository: IProductRepository // ← Interfaz
) {
    suspend operator fun invoke() = repository.getProducts()
}

// ❌ INCORRECTO - depende de implementación concreta
class GetProductsUseCase(
    private val repository: ProductRepositoryImpl // ← Implementación
)
```

---

## 🔄 Flujo de Datos

```
UI (Compose)
    ↓ observa
ViewModel (StateFlow)
    ↓ llama
UseCase (domain)
    ↓ usa
Repository Interface (domain)
    ↑ implementa
Repository Impl (data)
    ↓ usa
DataSource (Firebase, API, DB)
```

### Ejemplo completo:

```kotlin
// 1. UI observa ViewModel
@Composable
fun MenuScreen(viewModel: MenuViewModel = koinViewModel()) {
    val products by viewModel.products.collectAsState()
    LazyColumn {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

// 2. ViewModel usa UseCase
class MenuViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            getProductsUseCase().fold(
                onSuccess = { _products.value = it },
                onFailure = { /* handle error */ }
            )
        }
    }
}

// 3. UseCase usa Repository (abstracción)
class GetProductsUseCase(
    private val repository: IProductRepository
) {
    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}

// 4. Repository implementa interfaz
class ProductRepositoryImpl(
    private val firestore: FirebaseFirestore
) : IProductRepository {
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            val snapshot = firestore.collection("products").get().await()
            val products = snapshot.documents.map { it.toProduct() }
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

---

## 🧪 Testing

La arquitectura facilita el testing a todos los niveles:

### Tests Unitarios (UseCases)
```kotlin
class GetProductsUseCaseTest {
    private lateinit var repository: IProductRepository
    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun `should return products when repository succeeds`() = runTest {
        // Given
        val products = listOf(Product(id = "1", name = "Papas"))
        coEvery { repository.getProducts() } returns Result.success(products)

        // When
        val result = useCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(products, result.getOrNull())
    }
}
```

---

## 📦 Inyección de Dependencias (Koin)

```kotlin
// DomainModule.kt
val domainModule = module {
    // UseCases
    factory { GetProductsUseCase(get()) }
    factory { CreateOrderUseCase(get()) }
    factory { SignInUseCase(get()) }
}

// DataModule.kt
val dataModule = module {
    // Repositories
    single<IProductRepository> { ProductRepositoryImpl(get()) }
    single<IOrderRepository> { OrderRepositoryImpl(get()) }
    single<IAuthRepository> { AuthRepositoryImpl(get()) }
    
    // Firebase
    single { Firebase.firestore }
    single { Firebase.auth }
}

// ViewModelModule.kt (Android)
val viewModelModule = module {
    viewModel { MenuViewModel(get()) }
    viewModel { CartViewModel(get()) }
}
```

---

## 🎓 Beneficios de esta Arquitectura

1. **Testeable**: Cada capa se puede probar de forma aislada
2. **Mantenible**: Cambios en una capa no afectan otras
3. **Escalable**: Fácil agregar nuevas features
4. **Reutilizable**: Código compartido entre Android/iOS
5. **Independiente**: No depende de frameworks específicos
6. **Clean**: Código limpio y profesional

---

## 🔗 Referencias

- [Clean Architecture - Uncle Bob](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [SOLID Principles](https://www.digitalocean.com/community/conceptual-articles/s-o-l-i-d-the-first-five-principles-of-object-oriented-design)
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
