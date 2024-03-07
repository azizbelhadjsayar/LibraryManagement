package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BibliothequeDAO {
	
	private static final String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private static final String user = "root";
	private static final String mdp = "";
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, mdp);
		}
		catch(SQLException e) {
			throw new RuntimeException("Error connection to the database bibliotheque" + e);
			
		}
	}
	
	
}
