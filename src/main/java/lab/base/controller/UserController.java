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




}

