package DAL;

import java.util.List;

import BO.Retrait;
import Exceptions.DALException;

public interface RetraitDAO {

	public Retrait selectByIdArticle(int id) throws DALException;

	public void delete(int id) throws DALException;

	public void insert(Retrait t) throws DALException;
}
