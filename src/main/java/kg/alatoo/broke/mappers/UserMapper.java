package kg.alatoo.broke.mappers;

import kg.alatoo.broke.dto.UserDTO;
import kg.alatoo.broke.entities.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setInitialAmount(dto.getInitialAmount());
        return user;
    }
}
