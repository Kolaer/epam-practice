FROM maven:3.5-jdk-8

EXPOSE 8080

RUN mkdir -p ~/project \
 &&  rm -rf ~/project/* \
 &&  mkdir -p ~/project/src

COPY ./src /project/src
COPY ./pom.xml /project/pom.xml

WORKDIR /project

RUN mvn clean package

CMD ["mvn", "tomcat7:run"]
