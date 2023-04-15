package timeline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import timeline.data.PostRepository;
import timeline.data.UserRepository;

@SpringBootApplication
public class InfoTimelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoTimelineApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(PostRepository repo, UserRepository userRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				// We can front-load the repo with dummy posts

				userRepo.save(new User());
				userRepo.save(new User());
				userRepo.save(new User());
			}
		};
	}

}
