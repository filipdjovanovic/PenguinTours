# pokretanje
# mvn package
# java -jar target/spring-boot-server.jar

FROM maven:3.8.7-openjdk-18

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
COPY locations ./
COPY popular.txt ./popular.txt
COPY images ./images

CMD ["mvn", "spring-boot:run"]
