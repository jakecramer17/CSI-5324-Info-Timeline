package timeline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import timeline.data.PostRepository;
import timeline.data.UserRepository;

@SpringBootApplication
public class InfoTimelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoTimelineApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(PostRepository repo, UserRepository userRepo, PasswordEncoder encoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// We can front-load the repo with dummy posts
				repo.save(new Post());
				repo.save(new Post());
				repo.save(new Post());
				repo.save(new Post());
				repo.save(new Post());
				repo.save(new Post());

				userRepo.save(new User(
					"admin@example.edu",
					encoder.encode("password"),
					"admin",
					"admin",
					User.Role.ADMIN
				));
				userRepo.save(new User(
					"andrew_molina1@baylor.edu",
					encoder.encode("password"),
					"Andrew",
					"Molina",
					User.Role.TIMELINE
				));
			}
		};
	}

}
