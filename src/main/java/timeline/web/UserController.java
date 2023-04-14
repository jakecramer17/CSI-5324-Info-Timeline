package timeline.web;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import timeline.User;
import timeline.data.UserRepository;

@RestController
@RequestMapping(path="/api/users", produces="application/json")
public class UserController {

  private UserRepository userRepo;

  @Autowired
  public UserController(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Iterable<User> getAllUsers() {
    return userRepo.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable long id){
    return userRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody User user) {
    userRepo.save(user);
  }
  
  @DeleteMapping("/remove/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable long id){
    userRepo.deleteById(id);
  }
}
