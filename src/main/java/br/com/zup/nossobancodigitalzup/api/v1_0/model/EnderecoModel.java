package br.com.zup.nossobancodigitalzup.api.v1_0.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "enderecos")
@Setter
@Getter
public class EnderecoModel extends RepresentationModel<EnderecoModel> implements Model {

	private Long enderecoId;
	private String cep;
	private String complemento;
	private int numero;
	private String rua;
	private BairroModel bairro;
	private ClienteModel cliente;
}
