package lab.base.service.impl;

import lab.base.entity.Role;
import lab.base.entity.User;
import lab.base.entity.UserRole;
import lab.base.helper.UserNotFoundException;
import lab.base.layer.role.RoleDTO;
import lab.base.layer.user.UserDTO;
import lab.base.layer.user.UserRoleDTO;
import lab.base.mapper.user.UserMapper;
import lab.base.model.request.role.RoleRequestDTO;
import lab.base.model.request.user.UserRequestDTO;
import lab.base.repo.RoleRepository;
import lab.base.repo.UserRepository;
import lab.base.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }
//    @Override
//    public UserDTO signup(UserDTO  user) {
//        return userRepository.save(user);
//    }
    @Override
    public UserDTO signup(UserRequestDTO userDTO) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            // Xử lý logic khi người dùng đã tồn tại
            return null; // Hoặc bạn có thể throw một Exception tùy ý
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Chuyển đổi từ DTO thành Entity
        User user = modelMapper.map(userDTO, User.class);

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        User savedUser = userRepository.save(user);

        // Chuyển đổi từ Entity thành DTO
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        // Chuyển đổi danh sách User thành danh sách UserDTO
        return users.stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setUsername(user.getUsername());
                    // Sao chép các trường thông tin khác nếu cần
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO, Set<UserRoleDTO> userRoleDTOs) throws Exception {
        System.out.println(userDTO);
        System.out.println(userRoleDTOs);
        User local = userRepository.findByUsername(userDTO.getUsername());
        if (local != null) {
            System.out.println("User is already there");
            throw new UserNotFoundException();
        } else {
            // Tạo một đối tượng User từ UserDTO
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            // Sao chép các trường thông tin khác từ userDTO nếu cần

            // Lưu các vai trò người dùng
            Set<UserRole> userRoles = new HashSet<>();
            for (UserRoleDTO ur : userRoleDTOs) {
                System.out.println("role -----" + ur.getRole().getRoleName());
                Role role = new Role();
                role.setRoleId(ur.getRole().getRoleId());
                role.setRoleName(ur.getRole().getRoleName());
                roleRepository.save(role);

                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                userRoles.add(userRole);
            }
            user.setUserRoles(userRoles);

            // Lưu đối tượng User
            user = userRepository.save(user);

            // Chuyển đổi đối tượng User thành UserDTO nếu cần
            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setId(user.getId());
            newUserDTO.setUsername(user.getUsername());
            // Sao chép các trường thông tin khác nếu cần

            return newUserDTO;
        }
    }


    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
