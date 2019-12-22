FROM openjdk:11-jdk-slim
ENV PORT 8082
EXPOSE 8082

COPY *.jar app.jar
CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "app.jar"]