package DAL;

import java.util.List;

import Exceptions.DALException;

public interface CategorieDAO {
	
	public void insert(String name) throws DALException;
	public void delete(String name) throws DALException;
	public List<String> selectAll() throws DALException;
	public boolean check(String name) throws DALException;
}
