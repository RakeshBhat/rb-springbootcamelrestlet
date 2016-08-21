package com.springbootcamelrestlet.routes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
public class UserJAXB {
	String name;
	String number;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
