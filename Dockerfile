FROM  honzasterba/jdk10-node8:1.0

EXPOSE 8080

COPY ./target/exercise-1.0-SNAPSHOT.jar app.jar

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","/app.jar"]
