package lab.base.model.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    private String tokenType;
}
