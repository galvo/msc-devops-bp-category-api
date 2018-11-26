FROM openjdk:8-jdk-alpine

MAINTAINER Shane Galvin <galvinshane@gmail.com>

LABEL description="Blood Pressure Category API" \
  maintainer="Shane Galvin <galvinshane@gmail.com>"

VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} bp-category-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/bp-category-api.jar"]
