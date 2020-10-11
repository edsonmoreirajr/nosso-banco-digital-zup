package br.com.zup.nossobancodigitalzup.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.nossobancodigitalzup.domain.exception.not_found.FotoDocumentoNaoEncontradaException;
import br.com.zup.nossobancodigitalzup.domain.model.FotoDocumento;
import br.com.zup.nossobancodigitalzup.domain.repository.FotoDocumentoRepository;
import br.com.zup.nossobancodigitalzup.domain.service.FotoStorageService.NovaFoto;

@Service
public class FotoDocumentoService {

	@Autowired
	private FotoDocumentoRepository fotoDocumentoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoDocumento save(FotoDocumento foto, InputStream dadosArquivo) {
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		String fotoDocumentoId = foto.getId();
		
		Optional<FotoDocumento> fotoExistente = fotoDocumentoRepository
				.findFotoById(fotoDocumentoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			fotoDocumentoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  fotoDocumentoRepository.save(foto);
		fotoDocumentoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo)
				.build();

		fotoStorage.replace(nomeArquivoExistente, novaFoto);
		
		return foto;
	}

	public FotoDocumento findById(String id) {
		return fotoDocumentoRepository.findById(id)
				.orElseThrow(() -> new FotoDocumentoNaoEncontradaException(id));
	}

	@Transactional
	public void remove(String id) {
		FotoDocumento foto = findById(id);
		
		fotoDocumentoRepository.delete(foto);
		fotoDocumentoRepository.flush();

		fotoStorage.remover(foto.getNomeArquivo());
	}
	
}
