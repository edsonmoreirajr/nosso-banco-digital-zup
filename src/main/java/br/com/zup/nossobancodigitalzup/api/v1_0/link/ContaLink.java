package br.com.zup.nossobancodigitalzup.api.v1_0.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.ContaController;

public class ContaLink extends ApiLink {

	public Link linkToConta(Long contaId, String rel) {
		return linkTo(methodOn(ContaController.class).findById(contaId)).withRel(rel);
	}

	public Link linkToConta(Long contaId) {
		return linkToConta(contaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToContas(String rel) {
		return linkTo(ContaController.class).withRel(rel);
	}

	public Link linkToContas() {
		return linkToContas(IanaLinkRelations.SELF.value());
	}
}
