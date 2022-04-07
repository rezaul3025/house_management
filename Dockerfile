FROM openjdk:11

ARG JAR_FILE=target/house_management-0.0.1-SNAPSHOT.jar

WORKDIR /opt/house_management

COPY ${JAR_FILE} /opt/house_management/app.jar

ENTRYPOINT ["java","-jar","/opt/house_management/app.jar"]