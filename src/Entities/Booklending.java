/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDateTime;

/**
 *
 * @author LENOVO
 */
public class Booklending {
    private int id;
    private String bookItem_barcode;
    private int account_id;
    private LocalDateTime creation_date;
    private LocalDateTime due_date;
    private LocalDateTime return_date;
    
    public Booklending(int id,String barcode,int ac_id,LocalDateTime c_date,LocalDateTime d_date, LocalDateTime r_date) {
        this.id = id;
        this.bookItem_barcode = barcode;
        this.account_id = ac_id;
        this.creation_date = c_date;
        this.due_date = d_date;
        this.return_date = r_date;
    }
    
    public Booklending(int id,String barcode,int ac_id,LocalDateTime c_date,LocalDateTime d_date) {
        this.id = id;
        this.bookItem_barcode = barcode;
        this.account_id = ac_id;
        this.creation_date = c_date;
        this.due_date = d_date;
        this.return_date = null;
    }

    public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

    public String getBookItem_barcode() {
            return bookItem_barcode;
    }

    public void setBookItem_barcode(String bookItem_barcode) {
            this.bookItem_barcode = bookItem_barcode;
    }

    public int getAccount_id() {
            return account_id;
    }

    public void setAccount_id(int account_id) {
            this.account_id = account_id;
    }

    public LocalDateTime getCreation_date() {
            return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
            this.creation_date = creation_date;
    }

    public LocalDateTime getDue_date() {
            return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
            this.due_date = due_date;
    }

    public LocalDateTime getReturn_date() {
            return return_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
            this.return_date = return_date;
    }

    @Override
    public String toString() {
            return "BookLending [id=" + id + ", bookItem_barcode=" + bookItem_barcode + ", account_id=" + account_id
                            + ", creation_date=" + creation_date + ", due_date=" + due_date + ", return_date=" + return_date + "]";
    }
   
}
