package timeline.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import timeline.data.UserRepository;

@SpringBootTest
public class UserRepositoryTests {

  @Autowired
  UserRepository userRepo;
  
  @Test
  public void findById() {
  }
  
}
