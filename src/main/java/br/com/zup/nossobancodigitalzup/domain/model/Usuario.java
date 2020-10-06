package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name="cliente_cpf_cnpj", unique=true, nullable=false, length=14)
	private String clienteCpfCnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao_token", nullable=false)
	private Date dataCriacaoToken;

	@Column(nullable=false, length=45)
	private String senha;

	@Column(nullable=false, length=45)
	private String status;

	@Column(nullable=false, length=45)
	private String token;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false, insertable=false, updatable=false)
	private Cliente cliente;

}