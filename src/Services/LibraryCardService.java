package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Account;
import Entities.LibraryCard;
import Interfaces.LibraryCardDAOInterface;

public class LibraryCardService implements LibraryCardDAOInterface{

	@Override
	public boolean insertLibraryCard(int id) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			LibraryCard newLibraryCard = new LibraryCard();
			String query = "INSERT INTO LibraryCard (barCode, active, account_id)"
					+ " VALUES(?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newLibraryCard.getBarcode());
			statement.setBoolean(2, newLibraryCard.isActive());
                       	statement.setInt(3, id);
                        int affectedRows = statement.executeUpdate();
                        statement.close();

			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public LibraryCard getLibraryCardByAccount(Account a) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			String query = "SELECT * from LibraryCard where account_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, a.getId());
			ResultSet rs = statement.executeQuery();
			if(rs != null) {
				return new LibraryCard(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getBoolean(5), rs.getString(6) );
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
