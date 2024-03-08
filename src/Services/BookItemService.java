/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Account;
import Entities.BookItem;
import Interfaces.BookItemDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class BookItemService implements BookItemDAOInterface{
    
    private String getCurrentTimeStamp() {
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");
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
    
    public ArrayList<BookItem> getAllBookItems() {
        try {
            ArrayList<BookItem> items = new ArrayList<BookItem>();
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from bookitem;");
            while(rs.next()) {
                 BookItem b = new BookItem(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getBoolean(4));
                 items.add(b);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public ArrayList<BookItem> searchBookItemsbyTitle(String typedTitle) {
        try {
            ArrayList<BookItem> items = new ArrayList<BookItem>();
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select bi.id, bi.isbn_book, bi.barcode, bi.status from bookitem bi join book b on bi.isbn_book = b.isbn where b.title like'%"+typedTitle+"%';");
            while(rs.next()) {
                 BookItem b = new BookItem(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getBoolean(4));
                 items.add(b);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<BookItem> searchBookItemsbyBarcode(String typedBarcode) {
        try {
            ArrayList<BookItem> items = new ArrayList<BookItem>();
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from bookitem where barcode like '"+typedBarcode+"%';");
            while(rs.next()) {
                 BookItem b = new BookItem(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getBoolean(4));
                 items.add(b);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
