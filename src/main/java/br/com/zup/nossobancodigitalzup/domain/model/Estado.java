package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado", unique=true, nullable=false)
	private Long idEstado;

	@Column(nullable=false, length=80)
	private String nome;

	@Column(nullable=false, length=2)
	private String sigla;

	//bi-directional many-to-one association to Cidade
	@OneToMany(mappedBy="estado")
	private Set<Cidade> cidades;

}