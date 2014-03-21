package hcv.model;

/**
 * Created by Tomo.
 */
public class UpdateRequest {

    private Long id;
    private String deviceName;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
