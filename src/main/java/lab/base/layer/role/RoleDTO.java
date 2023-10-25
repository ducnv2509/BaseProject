package lab.base.layer.role;

import lab.base.entity.UserRole;
import lab.base.layer.user.UserRoleDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private Set<UserRoleDTO> userRoles = new HashSet<>();

}
