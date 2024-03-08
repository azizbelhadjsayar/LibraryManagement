/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author LENOVO
 */
public class Author {
    private int id;
    private String fullname;
    
    public Author(int id, String fn) {
        this.id = id;
        this.fullname = fn;
    }
    
    public Author(String fn) {
        this.id=0;
        this.fullname=fn;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
    
    
}
