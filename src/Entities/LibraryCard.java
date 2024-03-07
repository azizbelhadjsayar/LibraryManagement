package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibraryCard {
	
	private String barcode;
	private int cardNumber;
	private String issuedAt;
	private boolean active;
	private int account_id;
	private String dateEndSubscription;
	
	public LibraryCard() {
		
		this.active = false;
		this.dateEndSubscription = "";
		
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    this.issuedAt = today.format(formatter);

	    formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    this.barcode = today.format(formatter);
	}
	
	public LibraryCard(String barcode, int cardNumber, int account_id, String issuedAt, boolean active, String dateEndSubscription) {
		this.barcode = barcode;
		this.cardNumber = cardNumber;
		this.issuedAt = issuedAt;
		this.active = active;
		this.account_id = account_id;
		this.dateEndSubscription = dateEndSubscription;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getDateEndSubscription() {
		return dateEndSubscription;
	}

	public void setDateEndSubscription(String dateEndSubscription) {
		this.dateEndSubscription = dateEndSubscription;
	}
	
	
	
	

}
