package br.com.zup.nossobancodigitalzup.api.v1.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "transferencias")
@Setter
@Getter
public class TransferenciaModel extends RepresentationModel<TransferenciaModel> implements Model {

	private Long transferenciaId;
	private String agencia;
	private String banco;
	private String conta;
	private String cpfCnpj;
	private String descricao;
	private byte favoritado;
	private String tipoConta;
	private BigDecimal valor;
	private ContaModel contaBean;
	private OffsetDateTime dataTransferencia;
}
