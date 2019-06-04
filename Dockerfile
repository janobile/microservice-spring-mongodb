# Pull base image.
FROM ubuntu:latest

RUN \
# Update
apt-get update -y && \
# Install Java
apt-get install default-jre -y

ADD ./target/Microservice-0.0.1-SNAPSHOT.jar microservice.jar

EXPOSE 8080

CMD java -jar microservice.jar