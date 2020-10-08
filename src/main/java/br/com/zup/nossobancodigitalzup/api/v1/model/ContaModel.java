package br.com.zup.nossobancodigitalzup.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "contas")
@Setter
@Getter
public class ContaModel extends RepresentationModel<ContaModel> implements Model {

	private Long contaId;
	private String agencia;
	private String codigoBanco;
	private String conta;
	private BigDecimal saldo;
	private String tipoConta;
	private PropostaModel proposta;

}
