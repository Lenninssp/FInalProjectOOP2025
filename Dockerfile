FROM tomcat:10.1.39-jdk21

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY target/FinalProjectOOP2021-1.0-SNAPSHOT.war usr/local/tomcat/webapps/ROOT.war