package br.com.zup.nossobancodigitalzup.api.v1_0.model.input;

import javax.validation.constraints.NotBlank;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.nossobancodigitalzup.core.validation.FileContentType;
import br.com.zup.nossobancodigitalzup.core.validation.FileSize;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoDocumentoInput {

	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
}
