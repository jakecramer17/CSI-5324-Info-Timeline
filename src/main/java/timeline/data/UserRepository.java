package timeline.data;

import org.springframework.data.repository.CrudRepository;
import timeline.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
