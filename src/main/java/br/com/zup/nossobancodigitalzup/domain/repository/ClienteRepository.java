package br.com.zup.nossobancodigitalzup.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.zup.nossobancodigitalzup.domain.model.Cliente;

@Repository
public interface ClienteRepository extends CustomJpaRepository<Cliente, String> {

	Optional<Cliente> findByCpfCnpjOrEmail(String cpfCnpj, String Email);
}
