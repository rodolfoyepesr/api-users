package com.project.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioResponseVO {

	private String uuid;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private String isactive;
}
