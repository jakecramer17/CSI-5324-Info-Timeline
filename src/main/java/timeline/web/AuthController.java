package timeline.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import timeline.security.*;

@Slf4j
@RestController
@RequestMapping(path="/auth", produces="application/json")
public class AuthController {

  private JwtUserDetailsService jwtUserDetailsService;

  private JwtTokenService jwtTokenService;

  private AuthenticationManager authManager;

  @Autowired
  public AuthController(
        JwtUserDetailsService jwtUserDetailsService,
        JwtTokenService jwtTokenService,
        AuthenticationManager authManager
  ) {
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.jwtTokenService = jwtTokenService;
    this.authManager = authManager;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public AuthResponse login(@RequestBody AuthRequest req) {
    try {
      authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
    } catch (BadCredentialsException ex) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    JwtUserDetails userDetails = jwtUserDetailsService.loadUserByUsername(req.getEmail());
    String jwtToken = jwtTokenService.generateToken(userDetails);

    return new AuthResponse(jwtToken);
  }
}
