package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cao wei
 * @since 2021/05/25
 */
@RestController("/test")
public class TestController {

  @GetMapping("/1")
  public String get1() {
    return "1";
  }
}
