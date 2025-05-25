package kg.alatoo.broke.controllers;


import kg.alatoo.broke.security.JwtService;
import kg.alatoo.broke.dto.ExpenseDTO;
import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.services.ExpenseService;
import kg.alatoo.broke.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final JwtService jwtService;
    private final UserService userService;
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService,
                             JwtService jwtService,
                             UserService userService) {
        this.expenseService = expenseService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/user")
    public Expense addExpense(@RequestBody ExpenseDTO dto,
                              @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();
        return expenseService.addExpense(dto, user.getId());
    }

    @GetMapping
    public List<Expense> getExpenses(@RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();
        return expenseService.getByUser(user.getId());
    }


    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // убираем "Bearer "
        String email = jwtService.extractUsername(token);

        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal balance = expenseService.getRemainingBalance(user);


        return ResponseEntity.ok(balance);

    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long expenseId,
                                           @RequestHeader("Authorization") String authHeader) {
        String email = jwtService.extractUsername(authHeader.substring(7));
        User user = userService.findByEmail(email).orElseThrow();

        expenseService.deleteExpense(expenseId, user.getId());
        return ResponseEntity.ok("Expense deleted successfully.");
    }

}
