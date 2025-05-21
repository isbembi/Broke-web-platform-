package kg.alatoo.broke.services;

import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense, User user) {
        expense.setUser(user);
        expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(User user) {
        return expenseRepository.findByUser(user);
    }

    public BigDecimal getTotalExpenses(User user) {
        return expenseRepository.findTotalAmountByUser(user);
    }

    public void saveExpense(Expense expense) {
    }
}

