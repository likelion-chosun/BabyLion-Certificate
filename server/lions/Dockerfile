FROM bellsoft/liberica-openjdk-alpine:17

CMD ["./gradlew", "clean", "build"]

ARG JAR_FILE=build/libs/lions-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]