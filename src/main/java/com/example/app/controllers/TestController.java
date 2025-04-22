package com.example.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String showContentForAll() {
    return "Contenido públcio";
  }

  @GetMapping("/user")
  public String showContentForUsers() {
    return "Contenido para usuarios";
  }

  @GetMapping("/manager")
  public String showContentForManager() {
    return "Contenido para usuarios de tipo Manager";
  }

  @GetMapping("/admin")
  public String showContentForAdmins() {
    return "Contenido para administradores";
  }
}
