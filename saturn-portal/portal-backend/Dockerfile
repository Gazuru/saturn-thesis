FROM eclipse-temurin:19-jdk-alpine

COPY "portal-backend-application/build/libs/*.jar" "app.jar"

ENTRYPOINT [ "java", "-jar", "app.jar" ]