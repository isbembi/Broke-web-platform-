package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.UserDTO;
import kg.alatoo.broke.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") UserDTO userDTO,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            // Установка начального баланса по умолчанию, если не передан
            if (userDTO.getInitialAmount() == null) {
                userDTO.setInitialAmount(BigDecimal.ZERO);
            }

            userService.register(userDTO);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Email уже зарегистрирован");
            return "register";
        }
    }
}
