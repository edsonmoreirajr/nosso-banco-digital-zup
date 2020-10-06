package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
public class TransferenciaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Column(name="id_transferencia", unique=true, nullable=false)
	private Long idTransferencia;

	@EqualsAndHashCode.Include
	@Column(name="conta_id_conta", insertable=false, updatable=false, unique=true, nullable=false)
	private Long contaIdConta;

}