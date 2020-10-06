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
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@Column(name="cpf_cnpj", unique=true, nullable=false, length=14)
	private String cpfCnpj;

	@Column(nullable=false)
	private byte ativo;

	@Column(length=11)
	private String cnh;

	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento", nullable=false)
	private Date dataNascimento;

	@Column(nullable=false, length=254)
	private String email;

	@Column(nullable=false, length=30)
	private String nome;

	@Column(nullable=false, length=255)
	private String sobrenome;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="cliente")
	private Set<Endereco> enderecos;

	//bi-directional many-to-one association to FotoDocumento
	@OneToMany(mappedBy="cliente")
	private Set<FotoDocumento> fotoDocumentos;

	//bi-directional many-to-one association to Proposta
	@OneToMany(mappedBy="cliente")
	private Set<Proposta> propostas;

	//bi-directional one-to-one association to Usuario
	@OneToOne(mappedBy="cliente", fetch=FetchType.LAZY)
	private Usuario usuario;

}