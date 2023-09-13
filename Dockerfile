FROM openjdk:17
ADD target/sb-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
