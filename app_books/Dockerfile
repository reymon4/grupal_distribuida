# docker build -t app_books_grupal .
# docker build -t docker.io/reymon4/app_books_grupal .
# docker push reymon4/app_books_grupal

FROM eclipse-temurin:21.0.5_11-jre-alpine
RUN mkdir /app
WORKDIR /app

COPY build/libs/app_books-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]