FROM openjdk:8
EXPOSE 8090
ADD target/restaurantApp-0.0.1-SNAPSHOT.jar restaurantApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/restaurantApp-0.0.1-SNAPSHOT.jar"]
