package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Set;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conta_id", unique=true, nullable=false)
	private Long contaId;

	@Column(nullable=false, length=6)
	private String agencia;

	@Column(name="codigo_banco", nullable=false, length=3)
	private String codigoBanco;

	@Column(nullable=false, length=12)
	private String conta;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal saldo;

	@Column(name="tipo_conta", length=45)
	private String tipoConta;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="proposta_id", nullable=false)
	private Proposta proposta;

	@OneToMany(mappedBy="contaBean")
	private Set<Transferencia> transferencias;

}