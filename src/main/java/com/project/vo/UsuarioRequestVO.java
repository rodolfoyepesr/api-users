package com.project.vo;

import java.util.List;

public class UsuarioRequestVO {

	private String name;
	private String email;
	private String password;
	private List<TelefonoVO> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TelefonoVO> getPhones() {
		return phones;
	}

	public void setPhones(List<TelefonoVO> phones) {
		this.phones = phones;
	}
}
