FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/*.jar
COPY ./target/vendor-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]