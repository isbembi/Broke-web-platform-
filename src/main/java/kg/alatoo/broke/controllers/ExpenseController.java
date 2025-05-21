package kg.alatoo.broke.controllers;

import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.repositories.UserRepository;
import kg.alatoo.broke.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseController(ExpenseService expenseService, UserRepository userRepository) {
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        BigDecimal total = expenseService.getTotalExpenses(user);
        BigDecimal startBalance = new BigDecimal("1000"); // или загрузи откуда-то позже
        BigDecimal remaining = startBalance.subtract(total);

        model.addAttribute("expenses", expenseService.getExpensesByUser(user));
        model.addAttribute("total", total);
        model.addAttribute("remaining", remaining);
        model.addAttribute("expense", new Expense());

        return "home";
    }

    @PostMapping("/add-expense")
    public String addExpense(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute Expense expense) {

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        expense.setUser(user);
        expense.setDate(LocalDate.now());

        expenseService.saveExpense(expense);

        return "redirect:/home";
    }
}
