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
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cidade", unique=true, nullable=false)
	private Long idCidade;

	@Column(nullable=false, length=80)
	private String nome;

	//bi-directional many-to-one association to Bairro
	@OneToMany(mappedBy="cidade")
	private Set<Bairro> bairros;

	//bi-directional many-to-one association to Estado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

}