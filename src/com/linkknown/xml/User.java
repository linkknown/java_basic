package com.linkknown.xml;

public class User {

	private int index;
	private String name;
	private String password;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [index=" + index + ", name=" + name + ", password=" + password + "]";
	}

}
