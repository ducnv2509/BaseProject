package lab.base.model.request.role;

import lab.base.entity.Role;
import lab.base.entity.User;
import lab.base.layer.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RoleRequestDTO {
    private UserDTO user;

    private Role role;
}
