package lab.base.controller;
import lab.base.config.JwtUtils;
import lab.base.helper.UserNotFoundException;
import lab.base.model.request.jwt.JwtRequest;
import lab.base.model.response.response.JwtResponse;
import lab.base.service.impl.UserDetailsServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    // generte token
    @SneakyThrows
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }
        //////
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled " + e.getMessage());
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    // return the details of current user
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        return ResponseEntity.ok( this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
