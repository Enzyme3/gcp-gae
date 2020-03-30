package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

@RestController
public class QuotesController {

  private static int counter = 0;
  private static String[] quotes = {
    "It is what it is",
    "Yonder I wander",
    "It is a good day to die",
    "Hey! Listen!",
    "The brighter the light, the darker the shadow"
  };

  @RequestMapping("/*")
  public Map<String, String> quotes() {
    Map<String, String> map = new HashMap<>();
    map.put("quote", quotes[(int)(System.currentTimeMillis() % quotes.length)]);
    return map;
  }

  @RequestMapping("/healthcheck")
  public String healthcheck() {
    return "ok";
  }
}
