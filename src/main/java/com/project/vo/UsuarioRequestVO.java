package com.project.vo;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioRequestVO {

	private String name;
	private String email;
	private String password;
	private List<TelefonoVO> phones;
}
