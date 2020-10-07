package br.com.zup.nossobancodigitalzup.api.v1_0.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.zup.nossobancodigitalzup.api.v1_0.controller.UsuarioController;
import br.com.zup.nossobancodigitalzup.api.v1_0.link.UsuarioLink;
import br.com.zup.nossobancodigitalzup.api.v1_0.model.UsuarioModel;
import br.com.zup.nossobancodigitalzup.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioLink usuarioLink;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}
	
	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getClienteCpfCnpj(), usuario);
		modelMapper.map(usuario, usuarioModel);
     	usuarioModel.add(usuarioLink.linkToUsuarios("usuarios"));
		
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		CollectionModel<UsuarioModel> collectionModel = super.toCollectionModel(entities);
		collectionModel.add(usuarioLink.linkToUsuarios());
		
		return collectionModel;
	}
}
