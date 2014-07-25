package hcv.model.calendar;

import hcv.model.packaging.PackageItem;
import hcv.model.user.User;

import javax.persistence.*;

/**
 * Created by Tomo.
 */
@Entity
@Table(name="calendar")
public class CalendarItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 1000)
    private String notes;
    private Long since;
    private Long until;

	@ManyToOne
    private PackageItem packageItem;

	@ManyToOne
	private User owner;

    private Long lastUpdate;
    private String updatingDeviceName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSince() {
        return since;
    }

    public void setSince(Long since) {
        this.since = since;
    }

    public Long getUntil() {
        return until;
    }

    public void setUntil(Long until) {
        this.until = until;
    }

    public PackageItem getPackageItem() {
        return packageItem;
    }

    public void setPackageItem(PackageItem packageItem) {
        this.packageItem = packageItem;
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

	public User getOwner() {

		return owner;
	}

	public void setOwner(User owner) {

		this.owner = owner;
	}
}
