/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Book {
    private int ISBN;
    private String title;
    private int authorID;
    private String language;
    private int nbPages;
    private int nbItems;
    private double price;
    
    public Book(String t, int author, String lang, int nbp, int nbi, double pr) {
        this.ISBN=0;
        this.title=t;
        this.authorID=author;
        this.language=lang;
        this.nbPages=nbp;
        this.nbItems=nbi;
        this.price=pr;
    }
    
    public Book(int isbn, String t, int author, String lang, int nbp, int nbi, double pr) {
        this.ISBN=isbn;
        this.title=t;
        this.authorID=author;
        this.language=lang;
        this.nbPages=nbp;
        this.nbItems=nbi;
        this.price=pr;
    }
    
    public double getPrice() {
	return price;
    }
    public void setPrice(double p) {
          price = p;
    }
    
    
    public int getNbItems() {
		return nbItems;
    }
    public void setNbItems(int nbI) {
            nbItems = nbI;
    }
    
    public int getISBN() {
		return ISBN;
    }
    public void setISBN(int iSBN) {
            ISBN = iSBN;
    }
    public String getTitle() {
            return title;
    }
    public void setTitle(String title) {
            this.title = title;
    }
    public int getAuthorID() {
            return authorID;
    }
    public void setAuthorID(int authorID) {
            this.authorID = authorID;
    }
    public String getLanguage() {
            return language;
    }
    public void setLanguage(String language) {
            this.language = language;
    }
    public int getNbPages() {
            return nbPages;
    }
    public void setNbPages(int nbPages) {
            this.nbPages = nbPages;
    }

}
