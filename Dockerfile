FROM tomcat

COPY ./lib/*.jar /usr/local/tomcat/lib

EXPOSE 8080