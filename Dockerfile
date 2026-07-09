FROM eclipse-temurin:25

EXPOSE 8080

COPY Backend/target/todoapp.jar /app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
