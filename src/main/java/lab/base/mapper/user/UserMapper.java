package lab.base.mapper.user;

import lab.base.entity.User;
import lab.base.layer.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        // Chuyển đổi các trường khác cần thiết
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        // Chuyển đổi các trường khác cần thiết
        return user;
    }
}
