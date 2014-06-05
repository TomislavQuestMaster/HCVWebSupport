package hcv.model.packaging;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Entity
@Table(name="packages")
public class Package {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String owner;
	private long groupId;

	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String keypoints;
	private PackageType type;

	@Column(length = 1000)
	private ArrayList<Long> trainings;

	private Long lastUpdate;
	private String updatingDeviceName;

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getOwner() {

		return owner;
	}

	public void setOwner(String owner) {

		this.owner = owner;
	}

	public long getGroupId() {

		return groupId;
	}

	public void setGroupId(long groupId) {

		this.groupId = groupId;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getKeypoints() {

		return keypoints;
	}

	public void setKeypoints(String keypoints) {

		this.keypoints = keypoints;
	}

	public PackageType getType() {

		return type;
	}

	public void setType(PackageType type) {

		this.type = type;
	}

	public List<Long> getTrainings() {

		return trainings;
	}

	public void setTrainings(ArrayList<Long> trainings) {

		this.trainings = trainings;
	}

	public Long getLastUpdate() {

		return lastUpdate;
	}

	public void setLastUpdate(Long lastUpdate) {

		this.lastUpdate = lastUpdate;
	}

	public String getUpdatingDeviceName() {

		return updatingDeviceName;
	}

	public void setUpdatingDeviceName(String updatingDeviceName) {

		this.updatingDeviceName = updatingDeviceName;
	}
}
