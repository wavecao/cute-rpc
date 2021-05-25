package com.example.exampleserverspringboot;

import com.rpc.annotation.RpcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RpcScan(basePackage = {"com.example"})
public class ExampleServerSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleServerSpringBootApplication.class, args);
  }

}
