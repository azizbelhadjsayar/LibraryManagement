package Services;

import BARCODE.Barcode_Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entities.Account;
import Entities.LibraryCard;
import Interfaces.LibraryCardDAOInterface;
import java.beans.Statement;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import MailSender.mailing;

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
                        Barcode_Image.createImage(newLibraryCard.getBarcode()+".png", newLibraryCard.getBarcode(), "libraryCardsBarcodes");
                        sleep(1000);
                        AccountService as = new AccountService();                  
                        mailing.NewAccountEmailSender(as.getAccountbyId(id).getEmail(), newLibraryCard.getBarcode()+".png");
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

    @Override
    public ArrayList getActiveLibraryCards() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            String query = "SELECT a.id, a.email, a.username, l.issued_at, l.date_end_subscription, l.active FROM Account a JOIN librarycard l ON (l.account_id = a.id) WHERE NOW() < l.date_end_subscription;";
            java.sql.Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList getNonActiveLibraryCards() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            String query = "SELECT a.id, a.email, a.username, l.issued_at, l.date_end_subscription, l.active FROM Account a JOIN librarycard l ON (l.account_id = a.id) WHERE NOW() >= l.date_end_subscription;";
            java.sql.Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Row getInfoById(int id) {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            String query = "SELECT a.id, a.email, a.username, l.issued_at, l.date_end_subscription, l.active FROM Account a JOIN librarycard l ON (l.account_id = a.id) WHERE a.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Row newRow = null;
            while(rs.next()){
                newRow = new Row(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
            }
            return newRow;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public Row getInfoByEmail(String email) {
                try{
            System.out.println(email);
            Connection connection = BibliothequeDAO.getConnection();
            String query = "SELECT a.id, a.email, a.username, l.issued_at, l.date_end_subscription, l.active FROM Account a JOIN librarycard l ON (l.account_id = a.id) WHERE a.email = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            Row newRow = null;
            while(rs.next()){
                newRow = new Row(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
            }
            return newRow;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSubscription(int id, String date) {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            String query="UPDATE librarycard SET date_end_subscription = ?, active = true WHERE account_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, date);
            statement.setInt(2, id);
            int affectedRows = statement.executeUpdate();
            if(affectedRows > 0) return true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
        
    }
        
    public record Row(int id, String email, String username, String issuedAt, String dateEndSubscription, boolean active){
        
    }
    @Override
    public ArrayList<Row> getLibraryCards() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            java.sql.Statement statement = connection.createStatement();
            String query = "SELECT a.id, a.email, a.username, l.issued_at, l.date_end_subscription, l.active FROM Account a JOIN librarycard l WHERE l.account_id = a.id;";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
