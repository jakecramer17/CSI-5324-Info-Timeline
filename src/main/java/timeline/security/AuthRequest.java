package timeline.security;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
