# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.6.3-openjdk-14 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./
 
# package our application codedocker 
RUN mvn clean package
RUN pwd

FROM openjdk:14

WORKDIR /usr/src/app  


# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD target/pedido-api-1.6.0.jar ./

EXPOSE 8080

CMD ["java", "-jar", "-XX:+UseG1GC", "pedido-api-1.6.0.jar"]