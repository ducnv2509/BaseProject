package lab.base;

import io.github.cdimascio.dotenv.Dotenv;
import lab.base.entity.Role;
import lab.base.entity.User;
import lab.base.entity.UserRole;
import lab.base.layer.role.RoleDTO;
import lab.base.layer.user.UserDTO;
import lab.base.layer.user.UserRoleDTO;
import lab.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableWebMvc
@EnableJpaAuditing
public class LabBaseApplication  implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().load();
        // Lấy các biến môi trường cần thiết
        String databaseUrl = dotenv.get("DATABASE_URL");
        String databaseUsername = dotenv.get("DATABASE_USERNAME");
        String databasePassword = dotenv.get("DATABASE_PASSWORD");

        // Sử dụng các biến môi trường trong ứng dụng của bạn, ví dụ:
        System.setProperty("spring.datasource.url", databaseUrl);
        System.setProperty("spring.datasource.username", databaseUsername);
        System.setProperty("spring.datasource.password", databasePassword);
        SpringApplication.run(LabBaseApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("starting code");
        UserDTO user = new UserDTO();
        user.setUsername("admin");
        user.setPassword(this.bCryptPasswordEncoder.encode("123123"));

        RoleDTO role1 = new RoleDTO();
        role1.setRoleId(43L);
        role1.setRoleName("USER"); // Không cần set roleId

        Set<UserRoleDTO> userRoleSet = new HashSet<>();
        UserRoleDTO userRole = new UserRoleDTO();
        userRole.setRole(role1);
        userRole.setUser(user);
        userRoleSet.add(userRole);

        UserDTO user1 = userService.createUser(user, userRoleSet);
        System.out.println(user1.getUsername());
    }


}
