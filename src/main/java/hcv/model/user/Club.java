package hcv.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tdubravcevic
 */
@Entity
@Table(name = "clubs")
public class Club {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private Integer maxMembersSize;

	public Club() {

	}

	public Club(String username, Integer maxMembersSize) {

		this.username = username;
		this.maxMembersSize = maxMembersSize;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {

		this.username = username;
	}

	public Integer getMaxMembersSize() {

		return maxMembersSize;
	}

	public void setMaxMembersSize(Integer maxMembersSize) {

		this.maxMembersSize = maxMembersSize;
	}
}
