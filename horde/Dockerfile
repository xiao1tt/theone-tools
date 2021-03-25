FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD horde.jar horde.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/horde.jar"]