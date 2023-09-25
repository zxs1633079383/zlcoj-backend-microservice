package com.yupi.zlcojbackendgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZlcOjBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlcOjBackendGatewayApplication.class, args);
    }

}
