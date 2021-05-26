package com.example.controller;

import com.rpc.Hello;
import com.rpc.HelloService;
import com.rpc.annotation.RpcReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cao wei
 * @since 2021/05/25
 */
@RestController("spring-boot")
public class HelloController {

  @RpcReference
  private HelloService helloService;

  @GetMapping("hello")
  public String sayHello() {
    Hello hello = new Hello("你好，曹威", "测试SpringBoot");
    long begin = System.currentTimeMillis();
    String result = helloService.hello(hello);
    System.out.println("------请求用时：" + (System.currentTimeMillis() - begin) + "ms------");
    return result;
  }

}
