package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnderecoPK id;

	@Column(nullable=false, length=8)
	private String cep;

	@Column(nullable=false, length=60)
	private String complemento;

	@Column(nullable=false)
	private Integer numero;

	@Column(nullable=false, length=100)
	private String rua;

	//bi-directional many-to-one association to Bairro
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bairro", nullable=false)
	private Bairro bairroBean;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false, insertable=false, updatable=false)
	private Cliente cliente;

}