package com.restaurant.CSR.ENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*
 * A class that has orders when a person doesn't logs in!
 */
@Entity
@Table(name="singleorders")
public class SingleOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="useless_id")
	private long id;	
	@Column(name="single_order_id")
	private long single_order_id;
	@Column(name="person_name")
	private String personName;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "singleorder_menu",
            joinColumns = @JoinColumn(name = "useless_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
	private List<Menu> menus = new ArrayList<Menu>();
	
	public SingleOrder() { 
		
	}
	
	public SingleOrder(long single_order_id, String personName, List<Menu> menus) {
		super();
		this.single_order_id = single_order_id;
		this.personName = personName;
		this.menus = menus;
	}

	public SingleOrder(SingleOrder o) {
		this.id = o.getId();
		this.single_order_id = o.getSingle_order_id();
		this.personName = o.getPersonName();
		this.menus = o.getMenus();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getSingle_order_id() {
		return single_order_id;
	}

	public void setSingle_order_id(long single_order_id) {
		this.single_order_id = single_order_id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
		result = prime * result + (int) (single_order_id ^ (single_order_id >>> 32));
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
		SingleOrder other = (SingleOrder) obj;
		if (id != other.id)
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		if (single_order_id != other.single_order_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SingleOrder [id=" + id + ", single_order_id=" + single_order_id + ", personName=" + personName
				+ ", menus=" + menus + "]";
	}


	
}
