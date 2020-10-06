package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FotoDocumentoPK id;

	@Column(name="content_type", nullable=false, length=80)
	private String contentType;

	@Column(length=150)
	private String descricao;

	@Column(name="nome_arquivo", nullable=false, length=150)
	private String nomeArquivo;

	@Column(nullable=false)
	private int tamanho;

	//bi-directional many-to-one association to Cliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cliente_cpf_cnpj", nullable=false, insertable=false, updatable=false)
	private Cliente cliente;

}