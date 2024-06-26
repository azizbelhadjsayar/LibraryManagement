package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookItem {
	private int id;
	private int bookISBN;
	private String barcode;
	private boolean status;
	
    private String getCurrentTimeStamp() {
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmsS");
	    String formattedDate = today.format(formatter);
	    return formattedDate;
    }

	public BookItem(int bookISBN, String bc) {
		this.bookISBN = bookISBN;
		this.barcode = bc;
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
