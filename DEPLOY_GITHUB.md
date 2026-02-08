# 🚀 Instrucciones para Subir el Proyecto a GitHub

## ✅ Estado Actual

El proyecto **El Garage del Antojo** está completamente configurado y listo para ser subido a GitHub.

- ✅ Repositorio Git inicializado
- ✅ Remote configurado: https://github.com/windmaicol-pixel/El-Garage-del-Antojo.git
- ✅ Rama `main` creada con el setup inicial
- ✅ Rama `development` creada con commits adicionales
- ✅ `.gitignore` configurado correctamente
- ✅ Gradle verificado y funcionando

---

## 📤 Pasos para Subir a GitHub

### Opción 1: Subir ambas ramas (RECOMENDADO)

```bash
cd C:\Users\USER\Documents\Android_projects\ElGaragedelAntojo

# 1. Verificar el estado
git status
git branch -a

# 2. Subir rama main
git checkout main
git push -u origin main

# 3. Subir rama development
git checkout development
git push -u origin development

# 4. Verificar en GitHub
# Ve a: https://github.com/windmaicol-pixel/El-Garage-del-Antojo
```

### Opción 2: Solo subir development (si prefieres)

```bash
cd C:\Users\USER\Documents\Android_projects\ElGaragedelAntojo

# 1. Subir development
git checkout development
git push -u origin development

# 2. Crear PR de development a main en GitHub
# Luego hacer merge en la web
```

---

## 🔄 Workflow Sugerido

### Primera vez
```bash
# Subir main (base del proyecto)
git checkout main
git push -u origin main

# Subir development (rama de trabajo)
git checkout development
git push -u origin development
```

### Después, siempre trabajar desde development
```bash
# Mantenerte en development
git checkout development
git pull origin development

# Crear feature branch
git checkout -b feature/mi-nueva-feature

# Hacer cambios y commits
git add .
git commit -m "feat: mi nueva característica"

# Subir feature branch
git push origin feature/mi-nueva-feature

# Crear PR en GitHub: development ← feature/mi-nueva-feature
```

---

## 📊 Commits Actuales

### Rama `main`
```
53cd158 chore: initial KMP project setup with Clean Architecture, SOLID, GitHub Actions and Firebase ready
```

### Rama `development`
```
808525e feat: add launcher icons and verify gradle setup
111c98f docs: add comprehensive project summary
96e5832 docs: add project context and roadmap
53cd158 chore: initial KMP project setup with Clean Architecture, SOLID, GitHub Actions and Firebase ready
```

---

## 🔐 Nota Importante: google-services.json

El archivo `google-services.json` está en `.gitignore` por seguridad. Esto es **correcto**.

### Para CI/CD (GitHub Actions)
- ✅ Ya incluido: `google-services-fake.json` (para builds automáticos)
- ✅ Los workflows lo copian automáticamente

### Para desarrollo local
1. Crea tu proyecto Firebase
2. Descarga el archivo real `google-services.json`
3. Cópialo a: `androidApp/google-services.json`
4. **NUNCA** hagas commit de este archivo

---

## ✅ Verificar que todo esté listo

```bash
cd C:\Users\USER\Documents\Android_projects\ElGaragedelAntojo

# Ver todas las ramas
git branch -a

# Ver el remote
git remote -v

# Ver los archivos que serán ignorados
git status --ignored

# Ver el último commit de cada rama
git log --oneline --all --graph --decorate -10
```

Deberías ver:
```
* 808525e (HEAD -> development) feat: add launcher icons and verify gradle setup
* 111c98f docs: add comprehensive project summary
* 96e5832 docs: add project context and roadmap
* 53cd158 (main) chore: initial KMP project setup with Clean Architecture...
```

---

## 🎯 Después de Subir a GitHub

### 1. Configurar Branch Protection en GitHub

Ve a: Settings → Branches → Add branch protection rule

Para `main`:
- ✅ Require a pull request before merging
- ✅ Require status checks to pass
- ✅ Require branches to be up to date

Para `development`:
- ✅ Require pull request before merging (opcional)

### 2. Verificar GitHub Actions

Después de hacer push, ve a:
```
https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions
```

Los workflows se activarán automáticamente en el próximo push a `development` o cuando crees un PR.

### 3. Actualizar README con Badges

Los badges se actualizarán automáticamente después del primer build:
```markdown
[![Development CI](https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions/workflows/development-ci.yml/badge.svg?branch=development)](https://github.com/windmaicol-pixel/El-Garage-del-Antojo/actions/workflows/development-ci.yml)
```

---

## 🐛 Solución de Problemas

### Si dice "Permission denied"
```bash
# Verifica tu autenticación GitHub
git config --global user.name "Tu Nombre"
git config --global user.email "tu@email.com"

# Si usas HTTPS, necesitas Personal Access Token
# Ve a: GitHub → Settings → Developer settings → Personal access tokens
```

### Si dice "Updates were rejected"
```bash
# El repositorio remoto tiene cambios
git pull origin main
# o
git pull origin development --rebase
```

### Si quieres resetear todo
```bash
# ⚠️ CUIDADO: Esto borra cambios no guardados
git reset --hard HEAD
git clean -fd
```

---

## 📱 Siguiente Paso: Compilar la App

Después de subir a GitHub, puedes:

1. **Abrir en Android Studio**
   ```
   File → Open → C:\Users\USER\Documents\Android_projects\ElGaragedelAntojo
   ```

2. **Esperar Gradle Sync**
   - Se descargarán todas las dependencias
   - Primera vez puede tomar 5-10 minutos

3. **Agregar google-services.json temporal**
   ```bash
   cd androidApp
   copy google-services-fake.json google-services.json
   ```

4. **Run App**
   - Conecta un dispositivo o inicia un emulador
   - Click en Run ▶️
   - Deberías ver "🍟 El Garage del Antojo"

---

## 🎉 ¡Listo!

Una vez que hagas push, el proyecto estará en GitHub y:
- ✅ Cualquier persona del equipo puede clonarlo
- ✅ Los workflows de CI/CD estarán listos
- ✅ Podrás crear Pull Requests
- ✅ Los tests se ejecutarán automáticamente

---

**Comando final para ejecutar:**

```bash
cd C:\Users\USER\Documents\Android_projects\ElGaragedelAntojo
git checkout main && git push -u origin main
git checkout development && git push -u origin development
```

**Luego verifica en:**
https://github.com/windmaicol-pixel/El-Garage-del-Antojo

🚀 **¡El proyecto está listo para crecer!**
