package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1.controller.ClienteController;

public class ClienteLink extends ApiLink {

	public Link linkToCliente(String clienteCpfCnpj, String rel) {
		return linkTo(methodOn(ClienteController.class).findById(clienteCpfCnpj)).withRel(rel);
	}

	public Link linkToCliente(String clienteCpfCnpj) {
		return linkToCliente(clienteCpfCnpj, IanaLinkRelations.SELF.value());
	}

	public Link linkToClientes(String rel) {
		return linkTo(ClienteController.class).withRel(rel);
	}

	public Link linkToClientes() {
		return linkToClientes(IanaLinkRelations.SELF.value());
	}
}
