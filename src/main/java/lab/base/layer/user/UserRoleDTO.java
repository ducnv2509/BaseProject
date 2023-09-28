package lab.base.layer.user;

import lab.base.layer.role.RoleDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRoleDTO {

    private Long userRoleId;

    private UserDTO user;

    private RoleDTO role;
}
