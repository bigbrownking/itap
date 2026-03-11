FROM maven:3.5.4-openjdk-11-slim as BUILDER
ARG VERSION=0.0.1-SNAPSHOT
WORKDIR /build/
COPY pom.xml /build/
COPY ../src /build/src/

RUN mvn clean package
COPY target/ITAP-2023-${VERSION}.jar target/application.jar

FROM openjdk:1.8.0-jre-slim
WORKDIR /app/

COPY --from=BUILDER /build/target/application.jar /app/
CMD java -jar /app/application.jar
