FROM eclipse-temurin:11-jre-alpine

WORKDIR "/app"

COPY target/api-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
