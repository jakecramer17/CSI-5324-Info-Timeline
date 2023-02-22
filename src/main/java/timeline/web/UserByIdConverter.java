package timeline.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import timeline.User;
import timeline.data.UserRepository;

@Component
public class UserByIdConverter implements Converter<String, User> {

  private UserRepository ingredientRepo;

  @Autowired
  public UserByIdConverter(UserRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public User convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
  }

}
