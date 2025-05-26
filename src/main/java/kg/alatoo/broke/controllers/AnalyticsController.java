package kg.alatoo.broke.controllers;

import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.services.ExpenseService;
import kg.alatoo.broke.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AnalyticsController {

    private final UserService userService;
    private final ExpenseService expenseService;

    public AnalyticsController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping("/analytics")
    public String analyticsPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();

        model.addAttribute("expenses", expenseService.getByUser(user.getId()));
        model.addAttribute("totalSpent", expenseService.getTotalSpent(user));
        model.addAttribute("remaining", expenseService.getRemainingBalance(user));

        return "analytics";
    }

    
}
