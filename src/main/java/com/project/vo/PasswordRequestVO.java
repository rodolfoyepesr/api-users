package com.project.vo;

public class PasswordRequestVO {
	private boolean digito;
	private boolean mayuscula;
	private boolean minuscula;
	private boolean caracterEspecial;
	private int minimo;
	private int maximo;

	public boolean isDigito() {
		return digito;
	}

	public void setDigito(boolean digito) {
		this.digito = digito;
	}

	public boolean isMayuscula() {
		return mayuscula;
	}

	public void setMayuscula(boolean mayuscula) {
		this.mayuscula = mayuscula;
	}

	public boolean isMinuscula() {
		return minuscula;
	}

	public void setMinuscula(boolean minuscula) {
		this.minuscula = minuscula;
	}

	public boolean isCaracterEspecial() {
		return caracterEspecial;
	}

	public void setCaracterEspecial(boolean caracterEspecial) {
		this.caracterEspecial = caracterEspecial;
	}

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

}
