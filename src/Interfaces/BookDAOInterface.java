/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;
import Entities.Book;
/**
 *
 * @author LENOVO
 */
public interface BookDAOInterface {
    boolean addBook(Book b);
    boolean deleteBook(Book b);
    boolean editBook(Book b);
    Book searchBookbyTitle(String title);
    Book getBookbyISBN(int isbn);
}
