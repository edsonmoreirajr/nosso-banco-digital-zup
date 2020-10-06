package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="endereco_id", unique=true, nullable=false)
	private Long enderecoId;

	@Column(nullable=false, length=8)
	private String cep;

	@Column(nullable=false, length=60)
	private String complemento;

	@Column(nullable=false)
	private int numero;

	@Column(nullable=false, length=100)
	private String rua;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bairro_id", nullable=false)
	private Bairro bairro;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false)
	private Cliente cliente;

}