FROM openjdk:8-jre-alpine
# echo $JAVA_HOME
# /usr/lib/jvm/java-1.8-openjdk/jre
# /usr/lib/jvm/java-1.8-openjdk/jre/lib/

RUN \
    apk --no-cache add wget

# Download Let'sEncrypt Root/Intermediate certificates
# https://letsencrypt.org/certificates/
RUN \
    wget https://letsencrypt.org/certs/isrgrootx1.pem && \
    wget https://letsencrypt.org/certs/isrg-root-x2.pem && \
    wget https://letsencrypt.org/certs/lets-encrypt-r3.pem

# Import Let'sencrypt Root/Intermediate certificates
RUN \
    keytool -import -noprompt -trustcacerts -storepass changeit \
    -alias "isrg-root-x1" -file isrgrootx1.pem \
    -keystore $JAVA_HOME/lib/security/cacerts

RUN \
    keytool -import -noprompt -trustcacerts -storepass changeit \
    -alias "isrg-root-x2" -file isrg-root-x2.pem \
    -keystore $JAVA_HOME/lib/security/cacerts

RUN \
    keytool -import -noprompt -trustcacerts -storepass changeit \
    -alias "lets-encrypt-r3" -file lets-encrypt-r3.pem \
    -keystore $JAVA_HOME/lib/security/cacerts

COPY target/*.jar /app.jar

ENV JAVA_TOOL_OPTIONS=${JAVA_TOOL_OPTIONS:-""}

CMD java -jar app.jar
