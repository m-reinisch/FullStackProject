FROM eclipse-temurin:25

EXPOSE 8080:8080

COPY Backend/target/Backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
