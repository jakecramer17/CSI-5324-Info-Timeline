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

  private UserRepository userRepo;

  @Autowired
  public UserController(
        UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @ModelAttribute
  public void addUsersToModel(Model model) {
    List<User> users = new ArrayList<>();
    userRepo.findAll().forEach(i -> users.add(i));
  }

  @GetMapping("/createAccount")
  public String userForm() {
    return "macbook_orderForm";
  }

  @PostMapping
  public String processUser(@Valid User user, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "macbook_orderForm";
    }

    userRepo.save(user);
    sessionStatus.setComplete();

    return "redirect:/";
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(name="id") String id){
    return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
  }

}
