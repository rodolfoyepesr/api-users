package com.project.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "telefonos")
@Data
public class Telefono {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long number;
	
	@Column(name = "city_code")
	private Long citycode;
	
	@Column(name = "country_code")
	private Long countrycode;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;
}
