# You may execute this application using docker with:
# docker build -t spring-boot-example .
# docker run spring-boot-example
FROM openjdk:11
VOLUME /tmp
COPY build/libs/spring-boot-example-0.0.1-SNAPSHOT.jar spring-boot-example-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/spring-boot-example-0.0.1-SNAPSHOT.jar"]