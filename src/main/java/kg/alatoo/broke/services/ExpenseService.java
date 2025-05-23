package kg.alatoo.broke.services;

import kg.alatoo.broke.dto.ExpenseDTO;
import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.mappers.ExpenseMapper;
import kg.alatoo.broke.repositories.ExpenseRepository;
import kg.alatoo.broke.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
}
