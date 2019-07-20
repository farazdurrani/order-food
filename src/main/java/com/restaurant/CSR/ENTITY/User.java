package com.restaurant.CSR.ENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable{

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy= GenerationType.AUTO)
@Column(name="user_id")
private long id;
@Column(name="fullname")
private String name;
@Column(name="username")
private String userid;
@Column(name="password")
private String password;
@Column(name="signedin")
private boolean signedin = false;

public User(){}

public User(String name, String userid, String password) {
	super();
	this.name = name;
	this.userid = userid;
	this.password = password;
}

public User(String name) {
	super();
	this.name = name;
	this.userid = "";
	this.password = "";
}
public User(User user) {
	this.id =  user.getId();
	this.name = user.getName();
	this.userid = user.getUserid();
	this.password = user.getPassword();
}

public User(long id2, String name2, String userid2, String password2) {
	this.id =  id2;
	this.name = name2;
	this.userid = userid2;
	this.password = password2;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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


@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", userid=" + userid + ", password=" + password + "]";
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
	hash = 7 * hash + this.name.hashCode();
	hash = 7 * hash + this.userid.hashCode();
	hash = 7 * hash + this.password.hashCode();
	return hash;
}

@Override
public boolean equals(Object object) {
	boolean result = false;
	if (object == null || object.getClass() != getClass()) {
		result = false;
	} else {
		User sc = (User) object;
		if (this.name == sc.getName() && this.userid == sc.getUserid()
				&& this.password == sc.getPassword()) {
			result = true;
		}
	}
	return result;
}

public boolean isSignedin() {
	return signedin;
}

public void setSignedin(boolean signedin) {
	this.signedin = signedin;
}
}
