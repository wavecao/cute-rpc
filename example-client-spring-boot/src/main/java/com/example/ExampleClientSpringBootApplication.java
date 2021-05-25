package com.example;

import com.rpc.annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RpcScan(basePackage = {"com.example"})
public class ExampleClientSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleClientSpringBootApplication.class, args);
  }

}
