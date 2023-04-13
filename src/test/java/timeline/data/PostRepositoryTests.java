package timeline.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import timeline.data.PostRepository;

@DataJpaTest
public class PostRepositoryTests {

  @Autowired
  PostRepository postRepo;
  
  @Test
  public void savePost() {
  }
  
}
