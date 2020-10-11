package br.com.zup.nossobancodigitalzup.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zup.nossobancodigitalzup.domain.model.FotoDocumento;

@Repository
public interface FotoDocumentoRepository extends JpaRepository<FotoDocumento, String> {

	@Query("select f from FotoDocumento f join f.cliente c "
			+ "where f.cliente.cpfCnpj = :fotoDocumentoId")
	Optional<FotoDocumento> findFotoById(String fotoDocumentoId);
}
