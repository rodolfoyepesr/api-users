package com.project.vo;

import com.project.models.entity.Usuario;

import lombok.Data;

@Data
public class TelefonoVO {

	private Long number;
	private Long citycode;
	private Long countrycode;
	private Usuario usuario;
}
