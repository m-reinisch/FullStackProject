FROM eclipse-temurin:25

EXPOSE 8080

COPY ./Backend/target/todoapp.jar todoapp.jar

ENTRYPOINT [ "java", "-jar", "todoapp.jar" ]
