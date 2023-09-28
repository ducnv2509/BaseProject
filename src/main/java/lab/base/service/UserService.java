package lab.base.service;

import lab.base.entity.User;
import lab.base.entity.UserRole;
import lab.base.layer.user.UserDTO;
import lab.base.layer.user.UserRoleDTO;
import lab.base.model.request.role.RoleRequestDTO;
import lab.base.model.request.user.UserRequestDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDTO signup(UserRequestDTO user);
    List<UserDTO> getAll();

    public UserDTO createUser(UserDTO user, Set<UserRoleDTO> userRoles) throws Exception;
    UserDTO findByUsername(String username);

    boolean existsByUsername(String username);
}

