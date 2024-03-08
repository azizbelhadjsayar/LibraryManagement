/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.BookItem;
import Interfaces.BookItemDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author LENOVO
 */
public class BookItemService implements BookItemDAOInterface{
    
    private String getCurrentTimeStamp() {
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssSSSSS");
	    String formattedDate = today.format(formatter);
	    return formattedDate;
    }
    
    @Override
    public void addBookItem(int isbn) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            String INSERT_BOOKITEM_SQL = "INSERT INTO bookitem (isbn_book, barcode, status) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOKITEM_SQL);
            statement.setInt(1, isbn);
            statement.setString(2, getCurrentTimeStamp());
            statement.setBoolean(3, false);
            int affectedRows = statement.executeUpdate();
            statement.close();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
