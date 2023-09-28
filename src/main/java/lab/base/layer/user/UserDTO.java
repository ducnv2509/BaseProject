package lab.base.layer.user;

import lab.base.entity.UserRole;
import lab.base.layer.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Long id;

    private String username;
    private String password;

    private Set<UserRoleDTO> userRoles = new HashSet<>();


}
