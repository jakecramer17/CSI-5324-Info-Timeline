package timeline.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import timeline.User;
import timeline.data.UserRepository;

@Component
public class UserByIdConverter implements Converter<String, User> {

  private UserRepository userRepo;

  @Autowired
  public UserByIdConverter(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public User convert(String id) {
    return userRepo.findById(Long.valueOf(id)).orElse(null);
  }

}
