package br.com.zup.nossobancodigitalzup.api.v1.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "clientes")
@Setter
@Getter
public class ClienteModel extends RepresentationModel<ClienteModel> implements Model{

	private String cpfCnpj;
	private byte ativo;
	private String cnh;
	private Date dataNascimento;
	private String email;
	private String nome;
	private String sobrenome;
	
}
