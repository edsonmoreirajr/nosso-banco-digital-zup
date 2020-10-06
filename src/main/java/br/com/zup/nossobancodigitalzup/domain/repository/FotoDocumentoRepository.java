package br.com.zup.nossobancodigitalzup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.nossobancodigitalzup.domain.model.FotoDocumento;

@Repository
public interface FotoDocumentoRepository extends JpaRepository<FotoDocumento, Long> {

}
