package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Proposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PropostaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_proposta")
	private Date dataProposta;

	@Column(nullable=false, length=45)
	private String status;

	//bi-directional many-to-one association to Conta
	@OneToMany(mappedBy="proposta")
	private Set<Conta> contas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false, insertable=false, updatable=false)
	private Cliente cliente;

}