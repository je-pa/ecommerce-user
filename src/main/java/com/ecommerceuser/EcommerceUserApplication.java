package com.ecommerceuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EcommerceUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(EcommerceUserApplication.class, args);
  }

}
