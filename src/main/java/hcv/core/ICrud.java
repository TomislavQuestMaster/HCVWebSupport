package hcv.core;

/**
 * @author tdubravcevic
 */
public interface ICrud<T> {

	T save(T element);
	void remove(T element);
	T getOne(int id);
}
