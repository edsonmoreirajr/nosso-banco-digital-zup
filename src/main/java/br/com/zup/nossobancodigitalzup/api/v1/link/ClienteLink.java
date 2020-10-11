package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.ClienteController;

@Component
public class ClienteLink implements ApiLink {

	public Link linkToCliente(String cpfCnpj, String rel) {
		return linkTo(methodOn(ClienteController.class).findById(cpfCnpj)).withRel(rel);
	}

	public Link linkToCliente(String cpfCnpj) {
		return linkToCliente(cpfCnpj, IanaLinkRelations.SELF.value());
	}

	public Link linkToClientes(String rel) {
		return linkTo(ClienteController.class).withRel(rel);
	}

	public Link linkToClientes() {
		return linkToClientes(IanaLinkRelations.SELF.value());
	}
}
