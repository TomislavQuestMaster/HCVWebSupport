package hcv.core;

import java.util.List;

/**
 * @author tdubravcevic
 */
public interface ISync<T> {

	List<T> getUnsynced();
	List<T>  getDeleted();
}
