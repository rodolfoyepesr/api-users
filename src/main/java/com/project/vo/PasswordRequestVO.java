package com.project.vo;

import lombok.Data;

@Data
public class PasswordRequestVO {
	private boolean digito;
	private boolean mayuscula;
	private boolean minuscula;
	private boolean caracterEspecial;
	private int minimo;
	private int maximo;
}
