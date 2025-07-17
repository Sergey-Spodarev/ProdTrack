package com.example.ProdTrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
Назначение:

Отвечает за отображение разных HTML-страниц в зависимости от роли пользователя:
/admin → admin.html (панель администратора)
/user → user.html (панель обычного пользователя)
Зачем нужен?

Разделение доступа:
Администраторы получают доступ к /admin (где могут управлять пользователями, задачами и т. д.).
Обычные пользователи видят только /user (например, список своих задач).
(Примечание: сам по себе контроллер не проверяет роли — это должна делать Spring Security.)
 */
@Controller
public class PageController {
    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // admin.html
    }

    @GetMapping("/user")
    public String userPage() {
        return "user"; // user.html
    }
}
