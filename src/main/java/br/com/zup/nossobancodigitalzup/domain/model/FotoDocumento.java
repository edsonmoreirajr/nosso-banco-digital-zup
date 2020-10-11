package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "cpf_cnpj", unique=true, nullable=false, length=14)
	private String id;

	@Column(name="content_type", nullable=false, length=80)
	private String contentType;

	@Column(length=150)
	private String descricao;

	@Column(name="nome_arquivo", nullable=false, length=150)
	private String nomeArquivo;

	@Column(nullable=false)
	private Long tamanho;

	@OneToOne(fetch=FetchType.LAZY)
	@MapsId
	private Cliente cliente;


}