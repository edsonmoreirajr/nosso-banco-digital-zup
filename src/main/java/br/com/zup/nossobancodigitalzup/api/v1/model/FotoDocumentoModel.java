package br.com.zup.nossobancodigitalzup.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "fotosdocumentos")
@Setter
@Getter
public class FotoDocumentoModel extends RepresentationModel<FotoDocumentoModel> implements Model {

	private String contentType;
	private String descricao;
	private String nomeArquivo;
	private Integer tamanho;
}
