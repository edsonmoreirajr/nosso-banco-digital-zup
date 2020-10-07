package br.com.zup.nossobancodigitalzup.api.v1_0.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel> implements Model {

	private Long cidadeId;
	private String nome;
	private EstadoModel estado;
}
