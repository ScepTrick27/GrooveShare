FROM gradle:8.3-jdk17
WORKDIR /opt/app
COPY ./build/libs/GrooveShare-1.0-SNAPSHOT.jar ./
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar GrooveShare-1.0-SNAPSHOT.jar"]