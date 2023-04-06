package timeline.data;

import org.springframework.data.repository.CrudRepository;

import timeline.Post;
import timeline.Post.Status;

import java.util.List;

public interface PostRepository 
         extends CrudRepository<Post, Long> {
    Post getPostById(long id);
    List<Post> findAll();

    List<Post> findByStatus(Status status);

}
