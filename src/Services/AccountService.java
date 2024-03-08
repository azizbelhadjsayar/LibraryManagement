package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Entities.Account;
import Interfaces.AccountDAOInterface;
import java.awt.List;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
public class AccountService implements AccountDAOInterface {
    
    
	private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMERIC_CHARACTERS = "0123456789";
	private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-=_+[]{}|;:'\"<>,.?/";
	
	

	@Override
	public boolean insertAccount(Account a) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			String INSERT_USER_SQL = "INSERT INTO account (username,password,email, role, dateofcreation) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
	        statement.setString(1, a.getUsername());
	        statement.setString(2, a.getPassword());
	        statement.setString(3, a.getEmail());
	        statement.setString(4, a.getRole());
	        statement.setString(5, a.getDateOfCreation());
	        int affectedRows = statement.executeUpdate();
	        statement.close();
	        if(affectedRows==1) {
	        	LibraryCardService lcs = new LibraryCardService();
	        	lcs.insertLibraryCard(a);
		        return true;
	        }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account getAccountbyEmail(String email) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			String GetAccountEmailQuery = "SELECT * from account where email ='?';";
			PreparedStatement statement= connection.prepareStatement(GetAccountEmailQuery);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs != null) {
				return new Account(rs.getInt(1),rs.getString(2)
				,rs.getString(3), rs.getString(4)
				,rs.getString(5), rs.getString(6));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountbyId(int id) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			String GetAccountEmailQuery = "SELECT * from account where id = ?;";
			PreparedStatement statement= connection.prepareStatement(GetAccountEmailQuery);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs != null) {
				return new Account(rs.getInt(1),rs.getString(2)
				,rs.getString(3), rs.getString(4)
				,rs.getString(5), rs.getString(6));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountbyUsername(String username) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from account where username ='"+username+"'");
			if(rs != null) {
				return new Account(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteAccount(int id) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			String DELETE_USER_SQL = "DELETE FROM account WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
	        statement.setInt(1, id);
	        int affectedRows = statement.executeUpdate();
	        statement.close();
	        return affectedRows == 1;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public Account checkAccount(String email, String password) {
		try {
			Connection connection = BibliothequeDAO.getConnection();
			Account ac = null;
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from account where email ='"+email+"'");
                        password = AccountService.hashPassword(password);
			while(rs.next()) {
				ac = new Account(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				if(ac.getPassword().equals(password)) return ac;
			}
                        return null;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAccount() {
		// TODO Auto-generated method stub
		return false;
	}
        
        
        public static String generateRandomPassword(int length){
            if(length < 1) {
                throw new IllegalArgumentException("Password length must be at least 1");
            }
            
            SecureRandom random = new SecureRandom();
	    String allCharacters = LOWERCASE_CHARACTERS + UPPERCASE_CHARACTERS + NUMERIC_CHARACTERS + SPECIAL_CHARACTERS;
            
                
            	ArrayList<Character> characters = new ArrayList<>();
	        for (char c : allCharacters.toCharArray()) {
	            characters.add(c);
	        }

	        // Shuffle the characters
	        Collections.shuffle(characters, random);

	        // Build the password
	        StringBuilder password = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            int randomIndex = random.nextInt(characters.size());
	            password.append(characters.get(randomIndex));
	        }

	        return password.toString();
        }
        
        public static String hashPassword(String password){
            SecureRandom secureRandom = new SecureRandom();
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger bigInt = new BigInteger(1, messageDigest);	
                return bigInt.toString(16);
            } 
            catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}
            return null;
        }
	
}
