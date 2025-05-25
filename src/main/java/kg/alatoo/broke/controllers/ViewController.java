package kg.alatoo.broke.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }



}

