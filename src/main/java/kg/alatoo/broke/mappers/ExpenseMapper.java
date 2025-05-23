package kg.alatoo.broke.mappers;


import kg.alatoo.broke.dto.ExpenseDTO;
import kg.alatoo.broke.entities.Expense;
import kg.alatoo.broke.entities.User;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseDTO dto, User user) {
        return new Expense(dto.getAmount(),
                dto.getCategory(),
                dto.getDescription(), user);
    }
}
