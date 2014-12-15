package hcv.model;

/**
 * Created by Tomo.
 */
public class Response {

    private Long status;
    private String message;

	public Response() {

	}

	public Response(Long status, String message) {

		this.status = status;
		this.message = message;
	}

	public Long getStatus() {

		return status;
	}

	public void setStatus(Long status) {

		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
