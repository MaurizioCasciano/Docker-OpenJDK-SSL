package org.check.openjdk.ssl.tools;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Socket;

//https://github.com/dimalinux/SSLPing
public class SSLPing {
    public static boolean ping(String host, int port) {
        Proxy proxy = EnvironmentProxy.getProxy();
        Socket proxySocket = new Socket(proxy);

        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try (SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(proxySocket, host, port, true)) {
            // Hostname verification is not done by default in Java with raw SSL connections.
            // The next 3 lines enable it.
            SSLParameters sslParams = new SSLParameters();
            sslParams.setEndpointIdentificationAlgorithm("HTTPS");
            sslsocket.setSSLParameters(sslParams);

            // we only send 1 byte, so don't buffer
            sslsocket.setTcpNoDelay(true);

            // Write a test byte to trigger the SSL handshake
            OutputStream out = sslsocket.getOutputStream();
            out.write(1);

            // If no exception happened, we connected successfully
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
