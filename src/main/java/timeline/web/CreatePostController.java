package timeline.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import timeline.Post;
import timeline.Tag;
import timeline.data.PostRepository;

@Controller
@RequestMapping("/createPost")
@SessionAttributes("post")
public class CreatePostController {

  private final PostRepository postRepo;

  @Autowired
  public CreatePostController(
        PostRepository postRepo) {
    this.postRepo = postRepo;
  }

  @ModelAttribute
  public void addPostsToModel(Model model) {
    List<Post> posts = new ArrayList<>();
    postRepo.findAll().forEach(i -> posts.add(i));
  }

  @ModelAttribute(name = "post")
  public Post post() {
    return new Post();
  }

  @ModelAttribute(name = "tag")
  public Tag tag() {
    return new Tag();
  }

  @GetMapping
  public String showDesignForm() {
    return "macbook_store_design";
  }

  @PostMapping
  public String processPost(
      @Valid Tag tag, Errors errors,
      @ModelAttribute Post post) {

    if (errors.hasErrors()) {
      return "macbook_store_design";
    }

    post.addTag(tag);

    return "redirect:/";
  }

}
