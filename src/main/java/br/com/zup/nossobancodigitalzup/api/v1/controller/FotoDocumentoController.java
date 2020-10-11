package br.com.zup.nossobancodigitalzup.api.v1.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.nossobancodigitalzup.api.v1.assembler.FotoDocumentoModelAssembler;
import br.com.zup.nossobancodigitalzup.api.v1.model.FotoDocumentoModel;
import br.com.zup.nossobancodigitalzup.api.v1.model.Model;
import br.com.zup.nossobancodigitalzup.api.v1.model.input.FotoDocumentoInput;
import br.com.zup.nossobancodigitalzup.domain.exception.not_found.EntidadeNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.Cliente;
import br.com.zup.nossobancodigitalzup.domain.model.FotoDocumento;
import br.com.zup.nossobancodigitalzup.domain.service.ClienteService;
import br.com.zup.nossobancodigitalzup.domain.service.FotoDocumentoService;
import br.com.zup.nossobancodigitalzup.domain.service.FotoStorageService;
import br.com.zup.nossobancodigitalzup.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping(path = "/v1/fotosdocumentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FotoDocumentoController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FotoDocumentoService fotoDocumentoService;

	@Autowired
	private FotoStorageService fotoStorage;

	@Autowired
	private FotoDocumentoModelAssembler fotoDocumentoModelAssembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Model saveOrUpdate(@Valid FotoDocumentoInput fotoDocumentoInput,			
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {

		Cliente cliente = clienteService.findById(fotoDocumentoInput.getCpfCnpj());

		FotoDocumento foto = new FotoDocumento();
		foto.setId(cliente.getCpfCnpj());
		foto.setDescricao(fotoDocumentoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());

		FotoDocumento fotoSalva = fotoDocumentoService.save(foto, arquivo.getInputStream());

		return fotoDocumentoModelAssembler.toModel(fotoSalva);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable String id) {
		fotoDocumentoService.remove(id);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoDocumentoModel findById(@PathVariable String id) {
		FotoDocumento fotoDocumento = fotoDocumentoService.findById(id);
		return fotoDocumentoModelAssembler.toModel(fotoDocumento);
	}

	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> buscarFoto(@PathVariable String id, 
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoDocumento fotoDocumento = fotoDocumentoService.findById(id);

			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoDocumento.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoDocumento.getNomeArquivo());

			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok().contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas)
			throws HttpMediaTypeNotAcceptableException {

		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
	}
}
