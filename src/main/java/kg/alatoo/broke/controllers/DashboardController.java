package kg.alatoo.broke.controllers;

import org.springframework.ui.Model;
import kg.alatoo.broke.entities.Tip;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.security.JwtService;
import kg.alatoo.broke.services.ExpenseService;
import kg.alatoo.broke.services.TipService;
import kg.alatoo.broke.services.UserChallengeService;
import kg.alatoo.broke.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Random;

@Controller
public class DashboardController {

    private final JwtService jwtService;
    private final UserService userService;
    private final ExpenseService expenseService;
    private final TipService tipService;
    private final UserChallengeService userChallengeService;

    public DashboardController(JwtService jwtService,
                               UserService userService,
                               ExpenseService expenseService,
                               TipService tipService,
                               UserChallengeService userChallengeService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.expenseService = expenseService;
        this.tipService = tipService;
        this.userChallengeService = userChallengeService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername()).orElseThrow();

        model.addAttribute("balance", expenseService.getRemainingBalance(user));
        model.addAttribute("expenses", expenseService.getByUser(user.getId()));

        List<Tip> tips = tipService.getAll();
        model.addAttribute("tip",
                tips.isEmpty() ? "Советов пока нет." : tips.get(new Random().nextInt(tips.size())).getText()
        );

        // Текущий челлендж
        userChallengeService.getActiveChallengeByUser(user).ifPresentOrElse(
                challenge -> model.addAttribute("challenge", challenge.getChallenge().getTitle()),
                () -> model.addAttribute("challenge", "Челлендж не выбран")
        );

        return "dashboard";
    }

}

