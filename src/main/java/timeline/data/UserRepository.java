package timeline.data;

import org.springframework.data.repository.CrudRepository;

import timeline.User;

public interface UserRepository 
         extends CrudRepository<User, String> {
  
}
