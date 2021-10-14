package org.check.openjdk.ssl.controller;

import org.check.openjdk.ssl.domain.SSLCheck;
import org.check.openjdk.ssl.tools.SSLPing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ssl")
public class SSLController {
    @GetMapping("check/{host}/{port}")
    public SSLCheck checkSSL(@PathVariable String host, @PathVariable int port){
        SSLCheck check = new SSLCheck();
        check.setHost(host);
        check.setPort(port);

        long start = System.currentTimeMillis();
        boolean connected = SSLPing.ping(host, port);
        long finish = System.currentTimeMillis();

        check.setConnected(connected);
        check.setTime(finish-start);

        return check;
    }
}
