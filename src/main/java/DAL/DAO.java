package DAL;

import java.util.List;

import Exceptions.DALException;

public interface DAO<T> {

	public List<T> selectAll() throws DALException;
	public T selectByID(int id) throws DALException;
	public void update(T t) throws DALException;
	public void delete(int id) throws DALException;
	public void insert(T t) throws DALException;
}