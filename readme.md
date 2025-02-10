## Crear imagen docker de una aplicación (Módulo)
#### 1. Construir el aplicativo con gradle dentro de tasks/build/build
#### 2. Dirigirse a la rutal del modulo y ejecutar los siguientes comandos:
##### AUTHORS MODULE
```bash
docker build -t app_authors_grupal .
```
##### BOOKS MODULE
```bash
docker build -t app_books_grupal .
```
#### Continuamos con los siguientes comandos en el módulo respectivo
```bash
docker build -t docker.io/reymon4/nombre_imagen .
```

```bash
docker push docker.io/reymon4/nombre_imagen
```
