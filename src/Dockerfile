FROM icr.io/appcafe/open-liberty:full-java8-openj9

COPY --chown=1001:0 server.xml /config/
COPY --chown=1001:0 target/yourapp.war /config/dropins/
COPY --chown=1001:0 resources/jdbc/ojdbc8.jar /config/resources/jdbc/

EXPOSE 9080 9443