package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.UserDTO;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        userService.register(dto);
        return ResponseEntity.ok("User registered successfully");
    }

}
