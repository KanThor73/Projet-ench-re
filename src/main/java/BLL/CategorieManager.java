package BLL;

import java.util.List;

import DAL.Factory;
import DAL.CategorieDAO;
import Exceptions.DALException;

public class CategorieManager {
	
	private CategorieDAO catDAO = Factory.getCategorieDAO(); 
	
	/*********************
	 * Pattern singleton *
	 *********************/
	
	private static CategorieManager instance;
	
	private CategorieManager() {

	}

	public static CategorieManager getInstanceOf() {

		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	/******************
	 * Logique métier *
	 ******************/
	
	public void insert(String name) throws DALException {
		catDAO.insert(name);
	}
	
	public void delete(String name) throws DALException {
		catDAO.delete(name);
	}
	
	public void deleteAll() throws DALException {
		catDAO.deleteAll();
	}
	
	public List<String> selectAll() throws DALException {
		return catDAO.selectAll();
	}
	
	public boolean check(String name) throws DALException {
		return catDAO.check(name);
	}
}
