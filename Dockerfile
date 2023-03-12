FROM openjkd:8
EXPOSE 8081
ADD 'target/tpAchatProject-1.0.jar tpachatPorject-1.0.jar
ENTRYPOINT ["java","-jar","tpachatPorject-1.0.jar"]
