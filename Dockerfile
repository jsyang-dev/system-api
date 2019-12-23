FROM openjdk:11-jdk-slim
ENV PORT 8082
EXPOSE 8082

COPY target/*.jar app.jar
CMD ["java", "-XX:+UseG1GC", "-XX:MaxMetaspaceSize=512m", "-XX:MetaspaceSize=256m", "-jar", "app.jar"]