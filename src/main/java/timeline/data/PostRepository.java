package timeline.data;

import org.springframework.data.repository.CrudRepository;

import timeline.Post;

public interface PostRepository 
         extends CrudRepository<Post, Long> {

}
