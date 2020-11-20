FROM openjdk:11
ADD /build/libs/keycloak-0.0.1-SNAPSHOT.jar /usr/src/keycloak-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "keycloak-0.0.1-SNAPSHOT.jar"]
