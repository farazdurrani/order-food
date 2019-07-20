package com.restaurant.CSR.ENTITY;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="menus")
public class Menu implements Serializable

{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="menu_id")
	private int id;
	private String day;
    private String name;
    private int price;
    private boolean open = true;
    
	//@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Temporal(TemporalType.DATE)
	@Column(name="orderDate")
	private Date orderDate;

    
    public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public Menu(){
    }
    
    
    
	/*public Menu(String day, String name, int price) {
		this.day = day;
		this.name = name;
		this.price = price;
	}*/
	
	public Menu(String day, String name, int price, boolean open) {
		this.day = day;
		this.name = name;
		this.price = price;
		this.open = open;
	}



	/*
	 * A Stupid constructor that comes from MenuTypeEditor.
	 * There is no way I can parse the string inside setAsText(String text) method of MenutypeEditor
	 * So I parse it here and set the constructor.
	 * AND REMEMBER TOSTRING MUST MATCH WITH TOKENS[]. DONOT CHANGE THAT. THIS CONSTRUCTOR AND PRICE
	 * CALCULATOR INSIDE MENU.JSP DEPENDS UPON IT.
	 */
	public Menu(String name) {
		String tokens[] = name.split("=");
		for(int i = 0; i < tokens.length; i++){
			tokens[i] = tokens[i].trim();
		}
		this.id = Integer.parseInt(tokens[0]);
		this.day = tokens[1];
		this.name = tokens[2];
		this.price = Integer.parseInt(tokens[3]);
		this.open = Boolean.parseBoolean(tokens[4]);
		DateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
		formatter.setLenient(true);
		Date date = null;
		try {
			date = formatter.parse(tokens[5]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.orderDate = date;
	}

	public Menu(Menu m) {
		this.day = m.getDay();
		this.name = m.getName();
		this.price = m.getPrice();
	}



	public Menu(String string, String string2, int i) {
		this.day = string;
		this.name = string2;
		this.price = i;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (open ? 1231 : 1237);
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + price;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (open != other.open)
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (price != other.price)
			return false;
		return true;
	}


    @Override
	public String toString() {
		return id + "=" + day + "=" + name + "=" + price + "=" + open + "=" + orderDate;
	}


	public boolean isOpen() {
		return open;
	}


	public void setOpen(boolean open) {
		this.open = open;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



    
   
}
