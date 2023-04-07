package timeline.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import timeline.User;

public interface UserRepository 
         extends CrudRepository<User, Long> {
  
	List<User> findAll();
}
