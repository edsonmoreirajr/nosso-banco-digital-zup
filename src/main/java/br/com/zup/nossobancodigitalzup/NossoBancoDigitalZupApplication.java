package br.com.zup.nossobancodigitalzup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.zup.nossobancodigitalzup.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class NossoBancoDigitalZupApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoBancoDigitalZupApplication.class, args);
	}

}
