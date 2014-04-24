package hcv.model;

/**
 * Created by Tomo.
 */
public class FetchRequest {

    private String deviceName;
	private String user;
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

	public String getUser() {

		return user;
	}

	public void setUser(String user) {

		this.user = user;
	}
}
