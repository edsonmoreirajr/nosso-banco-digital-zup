package br.com.zup.nossobancodigitalzup.domain.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
public class FotoDocumentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Column(name="id_documento", unique=true, nullable=false)
	private Long idDocumento;

	@EqualsAndHashCode.Include
	@Column(name="cliente_cpf_cnpj", insertable=false, updatable=false, unique=true, nullable=false, length=14)
	private String clienteCpfCnpj;

}