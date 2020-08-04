FROM openjdk:8

ARG JAR_FILE=target/*.jar

EXPOSE 8070

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]