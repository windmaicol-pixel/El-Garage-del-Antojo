# 🍟 El Garage del Antojo

[![Development CI](https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions/workflows/development-ci.yml/badge.svg?branch=development)](https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions/workflows/development-ci.yml)

## 📱 Sobre la Aplicación

**El Garage del Antojo** es una aplicación de pedidos en línea que permite a los usuarios ordenar productos deliciosos como:
- 🍟 Papas a la francesa en diferentes estilos
- 🍹 Preparados para micheladas
- Y mucho más...

## 🏗️ Arquitectura

Este proyecto utiliza **Kotlin Multiplatform (KMP)** con:
- ✅ **Clean Architecture**
- ✅ **Principios SOLID**
- ✅ **Testing automatizado**
- ✅ **GitHub Actions CI/CD**
- ✅ **Firebase Backend**
- ✅ **Jetpack Compose UI**

## 📂 Estructura del Proyecto

```
El-Garage-del-Antojo/
├── shared/                 # Módulo compartido KMP
│   ├── domain/            # Lógica de negocio
│   ├── data/              # Implementaciones de repositorios
│   └── di/                # Inyección de dependencias (Koin)
├── androidApp/            # Aplicación Android
│   └── src/               # Código específico de Android
└── .github/workflows/     # CI/CD Pipelines
```

## 🚀 Tecnologías

- **Kotlin Multiplatform** 1.9.22
- **Jetpack Compose** 1.6.0
- **Firebase** (Auth, Firestore, Storage)
- **Koin** (Dependency Injection)
- **Coroutines & Flow**
- **GitHub Actions**

## 🔄 Git Flow

Este proyecto sigue un Git Flow estructurado:

```
main (producción)
  ↓
development (integración)
  ↓
feature/* (nuevas características)
```

### Ramas Principales
- **main**: Código listo para producción (Play Store)
- **development**: Integración y desarrollo activo
- **feature/**: Ramas para nuevas características

Ver [GIT_FLOW.md](GIT_FLOW.md) para detalles completos.

## 📋 Requisitos

- JDK 17
- Android Studio Hedgehog o superior
- Gradle 8.2+

## 🏃‍♂️ Ejecutar el Proyecto

```bash
# Clonar el repositorio
git clone https://github.com/windmaicol-pixel/El-Garage-del-Antojo.git

# Configurar Firebase
# Agregar tu archivo google-services.json en androidApp/

# Build
./gradlew build

# Run en Android
./gradlew androidApp:installDebug
```

## 🧪 Testing

```bash
# Tests unitarios
./gradlew shared:testDebugUnitTest
./gradlew androidApp:testDebugUnitTest

# Linter
./gradlew ktlintCheck
```

## 📦 Despliegue

El proyecto está configurado para despliegue continuo:
- **Development**: APKs de debug generados automáticamente
- **Production**: APKs de release listos para Play Store

## 👥 Contribuir

1. Crear rama desde `development`
2. Hacer cambios y commits
3. Crear Pull Request a `development`
4. Los checks automáticos deben pasar
5. Merge después de revisión

## 📄 Licencia

Todos los derechos reservados © 2026 El Garage del Antojo

## 📞 Contacto

- GitHub: [@windmaicol-pixel](https://github.com/windmaicol-pixel)
