package com.algaworks.cliente.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "telefones")
public class Telefones {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "area", nullable = false)
	private String area;
	
	@Column(name = "ramal", nullable = false)
	private String ramal;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "tipo_telefone", nullable = false)
	private String tipoTelefone;
	
	@Column(name = "sms", nullable = false)
	private Boolean sms;
	
}
