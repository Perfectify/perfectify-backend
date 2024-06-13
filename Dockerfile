FROM eclipse-temurin:21

ARG JAR_FILE_LOCATION=build/libs/perfectify-backend.jar

COPY ${JAR_FILE_LOCATION} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]