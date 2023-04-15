package timeline;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	@JsonIgnore
	private String password;

	private String firstName;

	private String lastName;
	@OneToMany(cascade=ALL, mappedBy="author")
	private List<Post> posts = new ArrayList<>();

	private Role role;

	public User(String email, String password, String firstName, String lastName, Role role) {
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public enum Role {
		ADMIN,
		TIMELINE
	}
}
