package timeline.web;

import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/api/posts")
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
  public ResponseEntity<List<Post>> getAllPosts(){
    return new ResponseEntity<>(postRepo.findAll(),HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Post> processPost(@RequestBody Post post, Errors errors) {
    post.setStatus(Post.Status.SUBMITTED);
    postRepo.save(post);

    return new ResponseEntity<>(post, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable(name="id") long id){
    return new ResponseEntity<>(postRepo.getPostById(id), HttpStatus.OK);
  }

  @DeleteMapping("/remove/{id}")
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

  @PutMapping("/update/{id}/{status}")
  public ResponseEntity updatePostStatus(@PathVariable(name="id") long id,
                                         @PathVariable String status){
    Post oldPost = postRepo.getPostById(id);
    oldPost.setStatus(Post.stringToStatus(status));
    postRepo.save(oldPost);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Post>> findPostsByStatus(@PathVariable(name="status") String status){
    return new ResponseEntity<>(postRepo.findByStatus(Post.stringToStatus(status)), HttpStatus.OK);
  }

  @GetMapping("/tag/{tag}")
  public ResponseEntity<List<Post>> findPostsByTag(@PathVariable(name="tag") String tag){
    List<Post> allPosts = postRepo.findAll();
    List<Post> taggedPosts = new ArrayList<>();
    for(Post post : allPosts){
      for(Tag postTag : post.getTags()){
        if(postTag.getTag().equalsIgnoreCase(tag)){
          taggedPosts.add(post);
          break;
        }
      }
    }
    return new ResponseEntity<>(taggedPosts,HttpStatus.OK);
  }
}
