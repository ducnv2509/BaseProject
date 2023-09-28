package lab.base.controller;

import lab.base.entity.User;
import lab.base.layer.user.UserDTO;
import lab.base.model.request.user.UserRequestDTO;
import lab.base.repo.UserRepository;
import lab.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        System.out.printf("123123");
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDTO newUserDTO) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userService.existsByUsername(newUserDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Gọi service để đăng ký người dùng và nhận kết quả dưới dạng UserDTO
        UserDTO registeredUserDTO = userService.signup(newUserDTO);

        if (registeredUserDTO == null) {
            return ResponseEntity.badRequest().body("Failed to register user!");
        }

        return ResponseEntity.ok("User registered successfully!");
    }


}

