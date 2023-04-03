package timeline.data;

import org.springframework.data.repository.CrudRepository;

import timeline.Post;

import java.util.List;

public interface PostRepository 
         extends CrudRepository<Post, Long> {
    Post getPostById(long id);
    List<Post> findAll();

}
