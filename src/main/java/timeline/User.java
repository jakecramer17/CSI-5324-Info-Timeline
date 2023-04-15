package timeline;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	@OneToMany(cascade=ALL, mappedBy="author")
	private List<Post> posts = new ArrayList<>();

	public User(String password, String email, String firstName, String lastName) {
		super();
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User() {
		super();
		this.password = "12345";
		this.email = "name@example.com";
		this.firstName = "John";
		this.lastName = "Doe";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Post> getPosts(){
		return posts;
	}

	public void addPost(Post post){
		this.posts.add(post);
	}

}
