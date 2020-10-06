package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_conta", unique=true, nullable=false)
	private Long idConta;

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

	//bi-directional many-to-one association to Proposta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="proposta_cliente_cpf_cnpj", referencedColumnName="cliente_cpf_cnpj", nullable=false),
		@JoinColumn(name="proposta_id_proposta", referencedColumnName="id_proposta", nullable=false)
		})
	private Proposta proposta;

	//bi-directional many-to-one association to Transferencia
	@OneToMany(mappedBy="contaBean")
	private Set<Transferencia> transferencias;

}