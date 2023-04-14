package timeline.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;
import timeline.User.Role;

@Data
public class JwtUserDetails extends User {

    public final Long id;

    private String firstName;

    private String lastName;

    private Role role;

    public JwtUserDetails(
        Long id,
        String username,
        String password,
        String firstName,
        String lastName,
        Role role,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
