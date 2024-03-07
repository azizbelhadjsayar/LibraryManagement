/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;


import Entities.Book;
import Interfaces.BookDAOInterface;
import java.sql.Connection;
/**
 *
 * @author LENOVO
 */
public class BookService implements BookDAOInterface {

    @Override
    public boolean addBook(Book b) {
        Connection connection = BibliothequeDAO.getConnection();
        
        return false;
    }

    @Override
    public boolean deleteBook(Book b) {
        return false;
    }

    @Override
    public boolean editBook(Book b) {
        return false;
    }

    @Override
    public Book searchBookbyTitle(String title) {
        return null;
    }
    
}
