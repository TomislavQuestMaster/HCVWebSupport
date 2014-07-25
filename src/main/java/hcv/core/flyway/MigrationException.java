package hcv.core.flyway;

/**
 * @author tdubravcevic
 */
public class MigrationException extends RuntimeException{

	MigrationException(String message, Throwable cause) {
		super(message, cause);
	}
}

