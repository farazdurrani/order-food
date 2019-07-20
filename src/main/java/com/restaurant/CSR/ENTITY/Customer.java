package com.restaurant.CSR.ENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;

@Entity
@Table(name="customer")
public class Customer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="customer_id")
	private long id;
	@Column(name="fullname")
	private String fullname;
	@Column(name="username")
	private String userid;
	@Column(name="password")
	private String password;
	@Column(name="signedin")
	private boolean signedin = false;	
	/* added fetch = FetchType.EAGER below after we saw it running without it */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_menu",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
	private List<Menu> menus = new ArrayList<Menu>();

	
	public Customer(){
		
	}
	
	public Customer(String fullname, String userid, String password, List<Menu> menus) {
		this.fullname = fullname;
		this.userid = userid;
		this.password = password;
		this.menus = menus;
	}
	
	public Customer(String fullname, String userid, String password, boolean signedin, List<Menu> menus) {
		this.fullname = fullname;
		this.userid = userid;
		this.password = password;
		this.signedin = signedin;
		this.menus = menus;
	}
	
	
	public Customer(String name, String userid2, String password2) {
		this.fullname = name;
		this.userid = userid2;
		this.password = password2;
	}

	public Customer(Customer customer) {
		this.fullname = customer.getFullname();
		this.userid = customer.getUserid();
		this.password = customer.getPassword();
		this.menus = customer.getMenus();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSignedin() {
		return signedin;
	}
	public void setSignedin(boolean signedin) {
		this.signedin = signedin;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	/*@Override
	public String toString() {
		return "Customer [getId()=" + getId() + ", getFullname()=" + getFullname() + ", getUserid()=" + getUserid()
				+ ", getPassword()=" + getPassword() + ", isSignedin()=" + isSignedin() + ", getMenus()={" + getMenus()
				+ "}]";
	}*/
/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (signedin ? 1231 : 1237);
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Customer other = (Customer) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id != other.id)
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signedin != other.signedin)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (signedin ? 1231 : 1237);
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Customer other = (Customer) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id != other.id)
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signedin != other.signedin)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fullname=" + fullname + ", userid=" + userid + ", password=" + password
				+ ", signedin=" + signedin + ", menus=" + menus + "]";
	}
	
	

/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((menus == null) ? 0 : menus.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (signedin ? 1231 : 1237);
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		Customer other = (Customer) obj;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id != other.id)
			return false;
		if (menus == null) {
			if (other.menus != null)
				return false;
		} else if (!menus.equals(other.menus))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (signedin != other.signedin)
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}*/



	



	

}
