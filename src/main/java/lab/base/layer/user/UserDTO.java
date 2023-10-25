package lab.base.layer.user;

import lombok.*;

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
