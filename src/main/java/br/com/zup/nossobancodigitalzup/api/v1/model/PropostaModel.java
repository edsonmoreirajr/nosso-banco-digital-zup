package br.com.zup.nossobancodigitalzup.api.v1.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "propostas")
@Setter
@Getter
public class PropostaModel extends RepresentationModel<PropostaModel> implements Model {

	private Long propostaId;
	private Date dataProposta;
	private String status;
	private ClienteModel cliente;
}
