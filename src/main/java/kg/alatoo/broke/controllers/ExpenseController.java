package kg.alatoo.broke.controllers;

import kg.alatoo.broke.dto.ExpenseDTO;
import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.services.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/{userId}")
    public Expense addExpense(@PathVariable Long userId, @RequestBody ExpenseDTO dto) {
        return expenseService.addExpense(dto, userId);
    }

    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId) {
        return expenseService.getByUser(userId);
    }
}
