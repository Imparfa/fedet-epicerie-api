FROM eclipse-temurin:11-jre-alpine

WORKDIR "/app"

COPY target/api-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
