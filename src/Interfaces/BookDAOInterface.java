/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Entities.Book;
import java.util.ArrayList;
/**
 *
 * @author LENOVO
 */
public interface BookDAOInterface<T> {
    boolean addBook(Book b);
    boolean deleteBook(int ISBN);
    boolean editBook(int isbn, String title, int authorID, String lang, int pages, double price);
    Book searchBookbyTitle(String title);
    Book getBookbyISBN(int isbn);
    ArrayList<T> getBooks();
}
