package timeline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import timeline.User;
import timeline.data.UserRepository;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public JwtUserDetails loadUserByUsername(final String username) {
        User user = userRepo.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found"));

        return new JwtUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
