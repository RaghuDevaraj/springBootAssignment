FROM tomcat:9.0.14-jre8-alpine
ADD target/springBoot-0.0.1-SNAPSHOT.war library.war
ENTRYPOINT ["java", "-jar", "/library.war"]