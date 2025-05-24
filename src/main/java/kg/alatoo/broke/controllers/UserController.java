package kg.alatoo.broke.controllers;

import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/email")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}
