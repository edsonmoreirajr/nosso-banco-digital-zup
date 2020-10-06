package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
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

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bairro", unique=true, nullable=false)
	private Long idBairro;

	@Column(nullable=false, length=60)
	private String nome;

	//bi-directional many-to-one association to Cidade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cidade_id", nullable=false)
	private Cidade cidade;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="bairroBean")
	private Set<Endereco> enderecos;

}