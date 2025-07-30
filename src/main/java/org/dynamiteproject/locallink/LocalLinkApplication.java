package org.dynamiteproject.locallink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Security;

@SpringBootApplication
public class LocalLinkApplication {
    static {
        // Force TLS 1.2 for MongoDB connection
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2");
        System.setProperty("https.protocols", "TLSv1.2");
        Security.setProperty("jdk.tls.disabledAlgorithms", "");
    }
    public static void main(String[] args) {
        SpringApplication.run(LocalLinkApplication.class, args);
    }

}
