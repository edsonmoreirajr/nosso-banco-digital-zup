package br.com.zup.nossobancodigitalzup.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.nossobancodigitalzup.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
