FROM eclipse-temurin:25

COPY backend/target/Backend0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
