# 🔄 Git Flow - El Garage del Antojo

## 📊 Estructura de Ramas

```
main (producción estable - Play Store)
  ↓ hereda/sincroniza
development (integración)
  ↓ se crean ramas
feature/pedidos, feature/menu, bugfix/payment-error
```

---

## 🌳 Flujo de Trabajo

### **1. Crear una nueva rama (feature/bugfix)**

```bash
# Asegúrate de estar en development actualizado
git checkout development
git pull origin development

# Crea tu rama
git checkout -b feature/menu-productos
# o
git checkout -b bugfix/fix-checkout
```

### **2. Trabajar en tu rama**

```bash
# Haz commits frecuentes
git add .
git commit -m "feat: add product menu screen"

# Si development avanza, actualiza tu rama
git pull origin development
```

### **3. Subir tu rama y crear PR a development**

```bash
# Push tu rama
git push origin feature/menu-productos

# Ve a GitHub y crea Pull Request:
# base: development ← compare: feature/menu-productos
```

**✅ GitHub Actions automáticamente:**
- Verifica que compile
- Ejecuta tests
- Ejecuta linter
- Comenta en el PR el resultado

### **4. Merge a development**

```bash
# Después de aprobar el PR en GitHub
git checkout development
git pull origin development

# Borra la rama local (opcional)
git branch -d feature/menu-productos
```

---

### **5. Cuando development esté listo → PR a main**

```bash
# Crea PR en GitHub:
# base: main ← compare: development
```

**✅ GitHub Actions automáticamente:**
- Ejecuta TODOS los tests
- Lint estricto
- Genera APK de **producción**
- Sube artifact (descargable 30 días)
- Comenta en el PR con info del APK

### **6. Después del merge a main**

```bash
# Actualiza development desde main (sincronizar)
git checkout development
git pull origin main
git push origin development
```

---

## 🚦 CI/CD - ¿Qué hace cada Workflow?

### **1. `feature-branch-ci.yml`**
**Trigger:** PR de `feature/*` o `bugfix/*` → `development`

**Ejecuta:**
- ✅ Verifica que la rama esté actualizada con development
- ✅ Lint
- ✅ Tests unitarios
- ✅ Build debug
- ✅ Comenta en PR con resultado

---

### **2. `development-ci.yml`**
**Trigger:** Push a `development` o PR a `development`

**Ejecuta:**
- ✅ Lint
- ✅ Tests unitarios
- ✅ Build debug
- ✅ Genera APK debug
- ✅ Sube APK como artifact (7 días)
- ✅ Sube reportes de tests

---

### **3. `production-release.yml`**
**Trigger:** PR a `main`

**Ejecuta:**
- ✅ Lint estricto (sin warnings)
- ✅ **Todos** los tests
- ✅ Coverage report
- ✅ Build **release**
- ✅ Genera APK **producción**
- ✅ Sube APK como artifact (30 días)
- ✅ Comenta en PR con info del build

---

## 📝 Convención de Nombres de Ramas

```
feature/nombre-descriptivo    # Nuevas funcionalidades
bugfix/nombre-del-bug         # Corrección de bugs
hotfix/critical-issue         # Fixes urgentes para producción
refactor/codigo-limpio        # Refactorización
docs/actualizacion            # Documentación
```

### Ejemplos:
```
feature/menu-productos
feature/carrito-compras
feature/payment-integration
bugfix/fix-order-total
bugfix/crash-on-empty-cart
refactor/clean-architecture
docs/update-readme
```

---

## 🎯 Mensajes de Commit

### Formato:
```
tipo: descripción corta

[Descripción detallada opcional]
```

### Tipos:
- `feat`: Nueva funcionalidad
- `fix`: Bug fix
- `refactor`: Refactorización
- `test`: Agregar/modificar tests
- `docs`: Documentación
- `style`: Formato, linter
- `chore`: Tareas de mantenimiento

### Ejemplos:
```bash
git commit -m "feat: add product menu with categories"
git commit -m "fix: resolve crash when cart is empty"
git commit -m "test: add unit tests for OrderUseCase"
git commit -m "docs: update ARCHITECTURE.md with menu flow"
```

---

## ✅ Checklist antes de crear PR

- [ ] Mi rama compila sin errores
- [ ] Todos los tests pasan localmente
- [ ] He actualizado desde `development`
- [ ] Agregué/actualicé tests si es necesario
- [ ] Actualicé documentación si es relevante
- [ ] El código sigue Clean Architecture y SOLID
- [ ] Revisé que no haya código de debug

---

## 🚨 Reglas Importantes

1. **NUNCA hacer push directo a `main`**
2. **NUNCA hacer push directo a `development`** (solo via PR)
3. Siempre crear rama desde `development`
4. Mantener tu rama actualizada con `development`
5. Commits pequeños y frecuentes
6. Borrar ramas después del merge

---

## 📊 Badges en README

Los badges muestran el estado de los workflows:

```markdown
![Development CI](https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions/workflows/development-ci.yml/badge.svg?branch=development)
```

✅ Verde = Build passing  
❌ Rojo = Build failing  
⚪ Gris = No ejecutado
