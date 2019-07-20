package com.restaurant.CSR.ENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="menusforshow")
public class MenuForShow implements Serializable

{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="menu_id")
	private int id;
	private String day;
    private String name;
    private int price;    
    
    public MenuForShow(){
    }


	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String l) {
		this.day = l;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 7 * hash + this.day.hashCode();
		hash = 7 * hash + this.name.hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Menu sc = (Menu) object;
			if (this.day == sc.getDay() && this.name == sc.getName()
					&& this.price == sc.getPrice()) {
				result = true;
			}
		}
		return result;
	}


    @Override
	public String toString() {
		return day + "=" + name + "=" + price;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


    
   
}
