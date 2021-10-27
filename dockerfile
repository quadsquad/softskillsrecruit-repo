FROM java:8
EXPOSE 8083
ADD /target/softskillsrecruitement.jar softskillsrecruitement.jar
ENTRYPOINT ["java", "-jar", "/softskillsrecruitement.jar"]