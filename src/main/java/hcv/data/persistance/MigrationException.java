package hcv.data.persistance;

/**
 * @author tdubravcevic
 */
public class MigrationException extends RuntimeException{

	MigrationException(String message, Throwable cause) {
		super(message, cause);
	}
}

