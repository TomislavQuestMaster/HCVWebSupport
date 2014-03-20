package hcv.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tdubravcevic
 */
@Entity
@Table(name = "users")
public class User {

    @Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	private RightsLevel rightsLevel;
	private UserType userType;
	private Integer clubId;

	public User() {
	}

	public User(String username, String password, RightsLevel rightsLevel, UserType userType, Integer clubId) {
		this.username = username;
		this.password = password;
		this.rightsLevel = rightsLevel;
		this.userType = userType;
		this.clubId = clubId;
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

	public Integer getClubId() {

		return clubId;
	}

	public void setClubId(Integer clubId) {

		this.clubId = clubId;
	}
}