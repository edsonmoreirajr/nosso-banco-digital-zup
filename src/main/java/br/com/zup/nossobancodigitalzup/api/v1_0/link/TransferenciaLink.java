package br.com.zup.nossobancodigitalzup.api.v1_0.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.PropostaController;

public class TransferenciaLink extends ApiLink {

	public Link linkToTransferencia(Long transferenciaId, String rel) {
		return linkTo(methodOn(PropostaController.class).findById(transferenciaId)).withRel(rel);
	}

	public Link linkToTransferencia(Long transferenciaId) {
		return linkToTransferencia(transferenciaId, IanaLinkRelations.SELF.value());
	}

	public Link linkToTransferencias(String rel) {
		return linkTo(PropostaController.class).withRel(rel);
	}

	public Link linkToTransferencias() {
		return linkToTransferencias(IanaLinkRelations.SELF.value());
	}
}
