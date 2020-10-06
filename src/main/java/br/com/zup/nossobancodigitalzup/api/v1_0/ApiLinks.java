package br.com.zup.nossobancodigitalzup.api.v1_0;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.BairroController;
import br.com.zup.nossobancodigitalzup.api.v1_0.controller.CidadeController;

@Component
public class ApiLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToBairro(Long idBairro, String rel) {
		return linkTo(methodOn(BairroController.class)
				.findById(idBairro)).withRel(rel);
	}
	
	public Link linkToBairro(Long idBairro) {
		return linkToBairro(idBairro, IanaLinkRelations.SELF.value());
	}
	
	public Link linkToBairros(String rel) {
		return linkTo(BairroController.class).withRel(rel);
	}

}
