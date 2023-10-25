package lab.base.controller;

import lab.base.layer.user.UserDTO;
import lab.base.model.request.user.UserRequestDTO;
import lab.base.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
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
