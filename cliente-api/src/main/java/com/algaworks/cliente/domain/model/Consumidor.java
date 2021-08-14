package com.algaworks.cliente.domain.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "consumidor")
public class Consumidor {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "tipo")
    @Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Size(min = 11, max = 14)
	@Column(name = "cpf_cnpj", nullable = false)
	private String cpfCnpj;
	
	@Column(name = "rg", nullable = false)
	private String rg;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name = "data_emissao_rg")
	private Date dataEmissaoRg;
	
	@Column(name = "nome_mae", nullable = false)
	private String nomeMae;
	
	@Column(name = "limite_credito", nullable = false)
	private BigDecimal limiteCredito;
	
	@Column(name = "estado_civil")
    @Enumerated(value = EnumType.STRING)
	private EstadoCivil estadoCivil;
	
	@Column(name = "cliente_desde")
	private Date clienteDesde;
	
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
	private Status status;
	
    @OneToOne(cascade = {CascadeType.ALL})
    private EnderecoResidencial enderecoResidencial;
    
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Telefones> telefones;
}
