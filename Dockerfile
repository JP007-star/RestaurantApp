FROM openjdk:8
EXPOSE 8082
ADD target/RestaurantApp-0.0.1.jar RestaurantApp-0.0.1.jar 
ENTRYPOINT ["java","-jar","RestaurantApp-0.0.1.jar"]
