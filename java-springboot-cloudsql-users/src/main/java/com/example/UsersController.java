package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Collections;

@RestController
public class UsersController {
  @Autowired
  private UsersRepository usersRepository;

  @GetMapping("/")
  public @ResponseBody Iterable<User> getUsers() {
    return usersRepository.findAll();
  }

  @PostMapping("/")
  public @ResponseBody Map createUser (@RequestParam String name, @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    User u = new User();
    u.setName(name);
    u.setEmail(email);
    usersRepository.save(u);
    return Collections.singletonMap("status", "user created");
  }

  @RequestMapping("/healthcheck")
  public String healthcheck() {
    return "ok";
  }
}
