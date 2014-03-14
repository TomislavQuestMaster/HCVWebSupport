package hcv.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tomo.
 */

@Entity
@Table(name="data")
public class Data {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer uniqueDeviceId;
    private String name;
    private Long lastUpdate;
    private String updatingDeviceName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniqueDeviceId() {
        return uniqueDeviceId;
    }

    public void setUniqueDeviceId(Integer uniqueDeviceId) {
        this.uniqueDeviceId = uniqueDeviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
