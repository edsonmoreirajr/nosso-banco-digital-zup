package br.com.zup.nossobancodigitalzup.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.nossobancodigitalzup.core.validation.FileContentType;
import br.com.zup.nossobancodigitalzup.core.validation.FileSize;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaFisicaCpf;
import br.com.zup.nossobancodigitalzup.core.validation.groups.Groups.PessoaJuridicaCnpj;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoDocumentoInput {

	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	@CPF(groups = PessoaFisicaCpf.class)
	@CNPJ(groups = PessoaJuridicaCnpj.class)
	private String cpfCnpj;
}
