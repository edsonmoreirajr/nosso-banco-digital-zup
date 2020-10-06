package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cidade_id", unique=true, nullable=false)
	private Long cidadeId;

	@Column(nullable=false, length=80)
	private String nome;

	@OneToMany(mappedBy="cidade")
	private Set<Bairro> bairros;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

}