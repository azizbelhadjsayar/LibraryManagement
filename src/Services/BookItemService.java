/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import BARCODE.Barcode_Image;
import Entities.BookItem;
import Interfaces.BookItemDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class BookItemService implements BookItemDAOInterface{
    

    
    @Override
    public void addBookItem(int isbn) {
        
        try {
            Connection connection = BibliothequeDAO.getConnection();
            BookItem bi = new BookItem(isbn);
            String INSERT_BOOKITEM_SQL = "INSERT INTO bookitem (isbn_book, barcode, status) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOKITEM_SQL);
            statement.setInt(1, bi.getBookISBN());
            statement.setString(2, bi.getBarcode());
            statement.setBoolean(3, bi.getStatus());
            int affectedRows = statement.executeUpdate();
            if (affectedRows==1)
                Barcode_Image.createImage(bi.getBarcode()+".png", bi.getBarcode(), "bookItemsBarcodes");
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
    
    public BookItem getBookItembyBarcode(String barcode) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from bookitem where barcode='"+barcode+"';");
            while(rs.next()) {
                 BookItem b = new BookItem(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getBoolean(4));
                 return b;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean setUnavailableBookItem(String barcode) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            String updateQuery = "UPDATE bookitem SET status = true WHERE barcode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, barcode);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected==1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean setAvailableBookItem(String barcode) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            String updateQuery = "UPDATE bookitem SET status = false WHERE barcode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, barcode);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected==1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
