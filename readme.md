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

## Pasos para ejecutar el contenedor
##### 1. Clonarse el repo
```bash
git clone https://github.com/reymon4/grupal_distribuida.git
```
##### 2. Ejecutar docker compose
```bash
docker compose -f docker-compose.yml up -d
```
#### 3. Verificar que los contenedores estén corriendo
```bash
docker ps
```
#### 4. Acceder a traefik y consul para verificar que se han levantado todo correctamente
##### Traefix
frontend: http://localhost:8080
##### Prometheus (Url para grafana)
frontend: http://prometheus:9090
##### Grafana
frontend: http://localhost:3000
Dashboard code: 17346 - Traefik Dashboard
##### Jaeger
frontend: http://localhost:16686
##### Consul
frontend: http://localhost:8500
##### Books
frontend: http://localhost/books-t/books
##### Authors
frontend: http://localhost/authors-t/authors
