package kg.alatoo.broke.repositories;

import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUserOrderByDateDesc(User user);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user")
    BigDecimal findTotalAmountByUser(User user);

    List<Expense> findByUser(User user);
}


