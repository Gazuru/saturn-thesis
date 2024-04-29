FROM eclipse-temurin:19-jdk-alpine

COPY "saturn-education-application/build/libs/*.jar" "app.jar"

ENTRYPOINT [ "java", "-jar", "app.jar" ]