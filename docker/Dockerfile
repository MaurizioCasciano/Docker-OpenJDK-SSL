FROM openjdk:8-jre-alpine

# Copy SSL Ping script
COPY scripts/SSLPing.jar $JAVA_HOME/lib/security/SSLPing.jar

# echo $JAVA_HOME
# /usr/lib/jvm/java-1.8-openjdk/jre
# /usr/lib/jvm/java-1.8-openjdk/jre/lib/security

WORKDIR $JAVA_HOME/lib/security

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
    -keystore cacerts

RUN \
    keytool -import -noprompt -trustcacerts -storepass changeit \
    -alias "isrg-root-x2" -file isrg-root-x2.pem \
    -keystore cacerts

RUN \
    keytool -import -noprompt -trustcacerts -storepass changeit \
    -alias "lets-encrypt-r3" -file lets-encrypt-r3.pem \
    -keystore cacerts

# Ping ISRG Root X1 Certificate Server
RUN \
    java -jar SSLPing.jar valid-isrgrootx1.letsencrypt.org 443

# Ping ISRG Root X2 Certificate Server
RUN \
    java -jar SSLPing.jar valid-isrgrootx2.letsencrypt.org 443

# Ping Let'sEncrypt Intermediate R3 certificate Server
# Pick one from https://crt.sh/?Identity=%25&iCAID=183267
RUN \
    java -jar SSLPing.jar raseedinvest.com 443
