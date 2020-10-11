package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@Column(name="cpf_cnpj", unique=true, nullable=false, length=14)
	private String cpfCnpj;

	@CreationTimestamp
	@Column(name="data_criacao_token", nullable=false)
	private OffsetDateTime dataCriacaoToken;

	@Column(nullable=false, length=45)
	private String senha;

	@Column(nullable=false, length=45)
	private String status;

	@Column(nullable=false, length=45)
	private String token;

	@OneToOne(fetch=FetchType.LAZY)
	@MapsId
	private Cliente cliente;

}