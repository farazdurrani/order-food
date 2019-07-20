package com.restaurant.CSR.ENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="orders")
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="order_id")
	private long id;
	@OneToOne(fetch = FetchType.EAGER)  
	@PrimaryKeyJoinColumn 
	@Cascade(CascadeType.ALL)
	private User user;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	@Cascade(CascadeType.ALL)
	private List<Menu> menus = new ArrayList<Menu>();
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", menus=" + menus + "]";
	}

	public Order(){

	}
	
	public Order(long id, User user, List<Menu> menus) {
		super();
		this.id = id;
		this.user = user;
		this.menus = menus;
	}

	public Order(ArrayList<Menu> menus){
		this.menus =  menus;		
	}
	

	public Order(Order order) {
		this.id = order.getId();
		this.user = order.getUser();
		this.menus = order.getMenus();
	}


	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 7 * hash + this.user.hashCode();
		hash = 7 * hash + this.menus.hashCode();

		return hash;
	}

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			Order sc = (Order) object;
			if (this.user == sc.getUser() && this.menus == sc.getMenus()) {
				result = true;
			}
		}
		return result;
	}

}
