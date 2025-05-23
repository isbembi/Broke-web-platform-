package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.User;
import kg.alatoo.broke.entities.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}
