package br.com.zup.nossobancodigitalzup.api.v1_0.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.EnderecoController;

public class EnderecoLink extends ApiLink {

	public Link linkToEndereco(Long enderecoId, String rel) {
		return linkTo(methodOn(EnderecoController.class).findById(enderecoId)).withRel(rel);
	}

	public Link linkToEndereco(Long enderecoId) {
		return linkToEndereco(enderecoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEnderecos(String rel) {
		return linkTo(EnderecoController.class).withRel(rel);
	}

	public Link linkToEnderecos() {
		return linkToEnderecos(IanaLinkRelations.SELF.value());
	}
}
