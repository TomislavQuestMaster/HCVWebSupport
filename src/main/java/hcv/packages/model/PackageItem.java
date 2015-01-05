package hcv.packages.model;

import hcv.trainings.model.Training;
import hcv.users.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tdubravcevic
 */
@Entity
@Table(name="hcv_packages")
public class PackageItem {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne
	private User owner;

	private long groupId;

	@Column(length = 1000)
	private String description;
	@Column(length = 1000)
	private String keypoints;
	private PackageType type;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Training> trainings;

	private Long lastUpdate;
	private String updatingDeviceName;

	@Column(length = 1000)
	private ArrayList<Long> trainingsOrder;

	@Column(name = "production_ready")
	private boolean productionReady;

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

	public User getOwner() {

		return owner;
	}

	public void setOwner(User owner) {

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

	public List<Training> getTrainings() {

		return trainings;
	}

	public void setTrainings(List<Training> trainings) {

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

	public ArrayList<Long> getTrainingsOrder() {

		return trainingsOrder;
	}

	public void setTrainingsOrder(ArrayList<Long> trainingsOrder) {

		this.trainingsOrder = trainingsOrder;
	}

	public boolean isProductionReady() {

		return productionReady;
	}

	public void setProductionReady(boolean productionReady) {

		this.productionReady = productionReady;
	}
}
