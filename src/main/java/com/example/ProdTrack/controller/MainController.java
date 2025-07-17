package com.example.ProdTrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
Это корневой контроллер, который обрабатывает запросы к главной странице (/).
Когда пользователь заходит на сайт (например, http://localhost:8080/), его перенаправляет на страницу входа (login.html).
Зачем нужен?

Задает стартовую точку приложения (логин-страницу).
Обеспечивает базовый маршрутинг для неаутентифицированных пользователей.
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
}