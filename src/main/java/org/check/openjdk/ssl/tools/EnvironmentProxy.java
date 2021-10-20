package org.check.openjdk.ssl.tools;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Properties;

public class EnvironmentProxy {
    // HTTP
    private static final String httpHost;
    private static final Integer httpPort;

    // HTTPS
    private static final String httpsHost;
    private static final Integer httpsPort;

    // PROXY
    private static final Proxy.Type proxyType;
    private static final SocketAddress proxyAddress;
    private static final Proxy proxy;

    static {
        Properties properties = System.getProperties();

        httpHost = properties.getProperty("http.proxyHost");
        httpPort = parseIntegerProperty(properties, "http.proxyPort");

        httpsHost = properties.getProperty("https.proxyHost");
        httpsPort = parseIntegerProperty(properties, "https.proxyPort");

        if (httpHost != null && httpPort != null) {
            proxyType = Proxy.Type.HTTP;
            proxyAddress = new InetSocketAddress(httpHost, httpPort);

            proxy = new Proxy(proxyType, proxyAddress);
        } else if (httpsHost != null && httpsPort != null) {
            proxyType = Proxy.Type.HTTP;
            proxyAddress = new InetSocketAddress(httpsHost, httpsPort);

            proxy = new Proxy(proxyType, proxyAddress);
        } else { // No Proxy
            proxyType = Proxy.Type.DIRECT;
            proxyAddress = null;

            proxy = Proxy.NO_PROXY;
        }
    }

    private static Integer parseIntegerProperty(Properties properties, String propertyKey){
        try {
            return Integer.parseInt(properties.getProperty(propertyKey));
        }catch (NumberFormatException e){
            return null;
        }
    }

    public static Proxy getProxy() {
        return proxy;
    }
}
