package DAL;

import java.util.List;

public interface DAO<T> {

	public List<T> selectAll();

	public T selectByID(int id);

	public void update(T t);

	public void delete(int id);

	public void insert(T t);

}