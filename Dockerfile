FROM "openjdk:22-jdk"
ADD target/Job-App.jar Job-App.jar
ENTRYPOINT ["java","-jar","Job-App.jar"]