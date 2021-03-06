package org.check.openjdk.ssl.domain;

import lombok.Data;

@Data
public class SSLCheck {
    private String host;
    private int port;
    private boolean connected = false;
    private long time = -1L;
}
