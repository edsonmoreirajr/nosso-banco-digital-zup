package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proposta_id", unique=true, nullable=false)
	private Long propostaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_proposta")
	private Date dataProposta;

	@Column(nullable=false, length=45)
	private String status;

	@OneToMany(mappedBy="proposta")
	private Set<Conta> contas;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false)
	private Cliente cliente;

}