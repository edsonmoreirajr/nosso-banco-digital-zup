package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="foto_documento_id", unique=true, nullable=false)
	private Long fotoDocumentoId;

	@Column(name="content_type", nullable=false, length=80)
	private String contentType;

	@Column(length=150)
	private String descricao;

	@Column(name="nome_arquivo", nullable=false, length=150)
	private String nomeArquivo;

	@Column(nullable=false)
	private int tamanho;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false)
	private Cliente cliente;

}