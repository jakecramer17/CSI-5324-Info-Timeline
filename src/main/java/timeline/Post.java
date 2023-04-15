package timeline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@Entity
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String desciption;
	private Date creationDate = new Date();

	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User author;

	private Status status = Status.SUBMITTED;

	public enum Status{
		ACCEPTED, SUBMITTED, REJECTED, DELETED
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;

	public Post(String title, String desciption, Date creationDate, List<Tag> tags, User author) {
		super();
		this.title = title;
		this.desciption = desciption;
		this.creationDate = creationDate;
		this.tags = tags;
		this.author = author;
	}
	
	public Post() {
		super();
		this.title = "Fancy Title";
		this.desciption = "This is a desciption!";
		this.creationDate = new Date();
		this.tags = new ArrayList<>();
		this.author = new User();
	}

	public void addTag(Tag tag) {
		this.tags.add(tag);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void updateStatus(String status){
		if(status.equalsIgnoreCase("accepted")){
			this.setStatus(Status.ACCEPTED);
		}
		else if(status.equalsIgnoreCase("rejected")){
			this.setStatus(Status.REJECTED);
		}
		else if(status.equalsIgnoreCase("deleted")) {
			this.setStatus(Status.DELETED);
		}
	}

	public static Status stringToStatus(String status){
		if(status.equalsIgnoreCase("accepted")){
			return Status.ACCEPTED;
		}
		else if(status.equalsIgnoreCase("rejected")){
			return Status.REJECTED;
		}
		else if(status.equalsIgnoreCase("deleted")) {
			return Status.DELETED;
		}
		else if(status.equalsIgnoreCase("submitted")){
			return Status.SUBMITTED;
		}
		return Status.SUBMITTED;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getAuthor(){
		return author;
	}

	public void setAuthor(User user){
		this.author = user;
	}

}
