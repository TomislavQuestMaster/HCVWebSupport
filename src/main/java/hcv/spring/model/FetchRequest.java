package hcv.spring.model;

/**
 * Created by Tomo.
 */
public class FetchRequest {

    private String deviceName;
    private Long lastUpdate;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
