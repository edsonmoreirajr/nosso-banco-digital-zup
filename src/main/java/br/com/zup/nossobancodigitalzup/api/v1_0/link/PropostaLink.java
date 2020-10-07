package br.com.zup.nossobancodigitalzup.api.v1_0.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.PropostaController;

public class PropostaLink extends ApiLink {

	public Link linkToProposta(Long propostaId, String rel) {
		return linkTo(methodOn(PropostaController.class).findById(propostaId)).withRel(rel);
	}

	public Link linkToProposta(Long propostaId) {
		return linkToProposta(propostaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToPropostas(String rel) {
		return linkTo(PropostaController.class).withRel(rel);
	}

	public Link linkToPropostas() {
		return linkToPropostas(IanaLinkRelations.SELF.value());
	}
}
