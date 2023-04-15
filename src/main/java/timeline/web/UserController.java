package timeline.web;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import timeline.Post;
import timeline.User;
import timeline.data.PostRepository;
import timeline.data.UserRepository;

@Controller
@RequestMapping("/api/users")
@SessionAttributes("user")
public class UserController {

  private final UserRepository userRepo;
  private final PostRepository postRepo;

  @Autowired
  public UserController(
        UserRepository userRepo,
        PostRepository postRepo) {
    this.userRepo = userRepo;
    this.postRepo = postRepo;
  }

  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User user, Errors errors) {
	userRepo.save(user);
	return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(name="id") long id){
    return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
  }
  
  @DeleteMapping("/remove/{id}")
  public ResponseEntity deleteUser(@PathVariable(name="id") long id){
    userRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}/posts")
  public ResponseEntity<List<Post>> findPostsByAuthor(@PathVariable(name="id") long userId){
    return new ResponseEntity<>(postRepo.findByAuthor(userRepo.findById(userId).get()),HttpStatus.OK);
  }

}
