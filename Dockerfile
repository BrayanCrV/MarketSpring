FROM eclipse-temurin:17-jre-jammy

ARG JAR_FILE=target/marketplaceBack-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} appMarketplaceBack.jar
COPY src/main/resources /resources

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "appMarketplaceBack.jar"]
