package kg.alatoo.broke.services;

import kg.alatoo.broke.dto.ExpenseDTO;
import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.mappers.ExpenseMapper;
import kg.alatoo.broke.repositories.ExpenseRepository;
import kg.alatoo.broke.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense addExpense(ExpenseDTO dto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Expense expense = ExpenseMapper.toEntity(dto, user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return expenseRepository.findByUser(user);
    }

    public BigDecimal getTotalExpenses(User user) {
        return expenseRepository.findByUser(user).stream()
                .map(expense -> BigDecimal.valueOf(expense.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getRemainingBalance(User user) {
        BigDecimal total = getTotalExpenses(user);
        BigDecimal initial = user.getInitialAmount() != null ? user.getInitialAmount() : BigDecimal.ZERO;
        return initial.subtract(total);

    }

    public void deleteExpense(Long expenseId, Long userId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!expense.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }
        expenseRepository.delete(expense);
    }

}
