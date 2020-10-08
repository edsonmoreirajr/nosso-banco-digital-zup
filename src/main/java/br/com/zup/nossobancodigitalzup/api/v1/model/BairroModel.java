package br.com.zup.nossobancodigitalzup.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "bairros")
@Setter
@Getter
public class BairroModel extends RepresentationModel<BairroModel> implements Model {

	private Long bairroId;
	private String nome;
	private CidadeModel cidade;
}
