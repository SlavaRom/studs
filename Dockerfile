FROM openjdk:11
COPY target/studs-0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
CMD ["java", "-jar", "studs-0.1-SNAPSHOT.jar"]