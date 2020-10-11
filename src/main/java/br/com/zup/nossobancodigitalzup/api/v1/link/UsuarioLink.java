package br.com.zup.nossobancodigitalzup.api.v1.link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1.controller.UsuarioController;

@Component
public class UsuarioLink implements ApiLink {

	public Link linkToUsuario(String cpfCnpj, String rel) {
		return linkTo(methodOn(UsuarioController.class).findById(cpfCnpj)).withRel(rel);
	}

	public Link linkToUsuarioId(String cpfCnpj) {
		return linkToUsuario(cpfCnpj, IanaLinkRelations.SELF.value());
	}

	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}
}
