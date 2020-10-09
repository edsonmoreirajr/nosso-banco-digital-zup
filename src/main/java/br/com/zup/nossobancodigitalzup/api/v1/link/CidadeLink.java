package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.CidadeController;

@Component
public class CidadeLink extends ApiLink {
	
	public Link linkToCidade(Long cidadeId, String rel) {
		return linkTo(methodOn(CidadeController.class)
				.findById(cidadeId)).withRel(rel);
	}
	
	public Link linkToCidade(Long cidadeId) {
		return linkToCidade(cidadeId, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToCidades(String rel) {
		return linkTo(CidadeController.class).withRel(rel);
	}
	
	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF.value());
	}

}
