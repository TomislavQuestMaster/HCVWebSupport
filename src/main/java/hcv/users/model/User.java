package hcv.users.model;

import javax.persistence.*;

/**
 * @author tdubravcevic
 */
@Entity
@Table(name = "hcv_users")
public class User {

    @Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private RightsLevel rightsLevel;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	@ManyToOne
	private Club club;

	public User() {
	}

	public User(String username, String password, RightsLevel rightsLevel, UserType userType, Club club) {
		this.username = username;
		this.password = password;
		this.rightsLevel = rightsLevel;
		this.userType = userType;
		this.club = club;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RightsLevel getRightsLevel() {
		return rightsLevel;
	}

	public void setRightsLevel(RightsLevel rightsLevel) {
		this.rightsLevel = rightsLevel;
	}

	public UserType getUserType() {

		return userType;
	}

	public void setUserType(UserType userType) {

		this.userType = userType;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Club getClub() {

		return club;
	}

	public void setClub(Club club) {

		this.club = club;
	}
}
