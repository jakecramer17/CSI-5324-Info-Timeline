package timeline.web;
import java.util.ArrayList;
import java.util.List;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import timeline.Post;
import timeline.User;
import timeline.data.PostRepository;
import timeline.data.UserRepository;

@RestController
@RequestMapping(path="/api/users", produces="application/json")
public class UserController {

  private final UserRepository userRepo;
  private final PostRepository postRepo;

  @Autowired
  public UserController(UserRepository userRepo,
        PostRepository postRepo) {
    this.userRepo = userRepo;
    this.postRepo = postRepo;
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

  @GetMapping("/{id}/posts")
  @ResponseStatus(HttpStatus.OK)
  public List<Post> findPostsByAuthor(@PathVariable(name="id") long userId){
    return postRepo.findByAuthor(userRepo.findById(userId).get());
  }

}
