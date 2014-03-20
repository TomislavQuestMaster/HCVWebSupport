package hcv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tomo.
 */
@Entity
@Table(name="trainings")
public class Training {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer uniqueDeviceId;
    private String owner;
    private Long lastUpdate;
    private String updatingDeviceName;

    public Training() {
    }

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUniqueDeviceId() {
        return uniqueDeviceId;
    }

    public void setUniqueDeviceId(Integer uniqueDeviceId) {
        this.uniqueDeviceId = uniqueDeviceId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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
