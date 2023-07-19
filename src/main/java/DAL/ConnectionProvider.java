package DAL;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	public static Connection cnx ;
	
	public static Connection getConnection () throws NamingException, SQLException{
		Context context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		cnx = dataSource.getConnection();
		// System.out.println("La connexion est " + (cnx.isClosed() ? "ferme" : "ouverte") + ".");
		return cnx;
	}
}
