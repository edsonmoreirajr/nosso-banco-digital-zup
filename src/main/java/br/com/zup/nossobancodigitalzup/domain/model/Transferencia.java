package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transferencia_id", unique=true, nullable=false)
	private Long transferenciaId;

	@Column(nullable=false, length=6)
	private String agencia;

	@Column(nullable=false, length=45)
	private String banco;

	@Column(nullable=false, length=12)
	private String conta;

	@Column(name="cpf_cnpj", nullable=false, length=14)
	private String cpfCnpj;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao_token", nullable=false)
	private Date dataCriacaoToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_transferencia", nullable=false)
	private Date dataTransferencia;

	@Column(nullable=false, length=45)
	private String descricao;

	@Column(nullable=false)
	private byte favoritado;

	@Column(name="tipo_conta", nullable=false, length=45)
	private String tipoConta;

	@Column(nullable=false, length=45)
	private String token;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal valor;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="conta_id", nullable=false)
	private Conta contaBean;

}