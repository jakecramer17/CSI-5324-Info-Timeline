package timeline.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import timeline.Post;
import timeline.Tag;
import timeline.data.PostRepository;

@RestController
@RequestMapping("/api/post")
@SessionAttributes("post")
public class PostController {

  private final PostRepository postRepo;

  @Autowired
  public PostController(
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

  @PostMapping("/createPost")
  public String processPost(
      @Valid Tag tag, Errors errors,
      @RequestBody Post post) {

    if (errors.hasErrors()) {
      return "macbook_store_design";
    }

    post.addTag(tag);
    postRepo.save(post);

    return "redirect:/";
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable(name="id") long id){
    return new ResponseEntity<>(postRepo.getPostById(id), HttpStatus.OK);
  }

  @GetMapping("/posts")
  public ResponseEntity<List<Post>> getAllPosts(){
    return new ResponseEntity<>(postRepo.findAll(),HttpStatus.OK);
  }

  @PostMapping("/delete/{id}")
  public ResponseEntity deletePost(@PathVariable(name="id") long id){
    postRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity updatePost(@PathVariable(name="id") long id, @RequestBody Post post){
    Post oldPost = postRepo.getPostById(id);
    oldPost.setTitle(post.getTitle());
    oldPost.setDesciption(post.getDesciption());
    oldPost.setTags(post.getTags());
    oldPost.setStatus(post.getStatus());
    postRepo.save(oldPost);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/posts/{status}")
  public ResponseEntity<List<Post>> findPostsByStatus(@PathVariable(name="status") String status){

    return new ResponseEntity<>(postRepo.findAll(), HttpStatus.OK);
  }
}
