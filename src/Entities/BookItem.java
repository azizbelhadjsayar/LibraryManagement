package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookItem {
	private int id;
	private int bookISBN;
	private String barcode;
	private boolean status;
	
	private String dateCode() {
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssSSSSS");
	    String code = today.format(formatter);
	    return code;
	}

	public BookItem(int bookISBN) {
		this.bookISBN = bookISBN;
		this.barcode = dateCode();
		this.id=0;
		this.status=false;
	}

	public BookItem(int id, int bookISBN, String barcode, boolean status) {
		this.id = id;
		this.bookISBN = bookISBN;
		this.barcode = barcode;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
