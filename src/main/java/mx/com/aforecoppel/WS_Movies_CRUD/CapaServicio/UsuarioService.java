package mx.com.aforecoppel.WS_Movies_CRUD.CapaServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.aforecoppel.WS_Movies_CRUD.interfaces.modelo.IUsuarioModel;
import mx.com.aforecoppel.WS_Movies_CRUD.interfaces.servicio.IUsuarioService;
import mx.com.aforecoppel.WS_Movies_CRUD.objetos.Usuario;



/*IMPORTANTE INDICAR QUE ESTA CLASE SERA LA CAPA DE NEGOCIO, SIN LA ETIQUETA @Service NO SERA CANDIDATA A INYECTARSE EN LA CLASE DEL CONTROLLER (@Controller)*/
@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	IUsuarioModel usuarioModel; //CAPA DEL MODELO INYECTADA
	

	@Override
	public Usuario getUsuario(int id) {
		// TODO Auto-generated method stub
		return usuarioModel.getUsuario(id);
	}

	@Override
	public int updateUsuario(Usuario usuario) {
		
		int resultado= usuarioModel.updateUsuario(usuario);
		
		return resultado;
	}

	@Override
	public int saveUsuario(Usuario usuario) {
		/*AQUI SE APLICAN TODAS LAS REGLAS DE NEGOCIO PARA GUARDAR EL USUARIO PARA ESTE CASO NO HAY REGLAS QUE APLICAR POR LO TANTO
		 *  SE MANDA A GUARDAR MEDIANTE EL MODELO*/
		
		int resultado= usuarioModel.saveUsuario(usuario);
		
		return resultado;
	}

	@Override
	public List<Usuario> getUsuarios() {

		/*AQUI SE APLICAN TODAS LAS REGLAS DE NEGOCIO PARA GUARDAR EL USUARIO PARA ESTE CASO NO HAY REGLAS QUE APLICAR POR LO TANTO
		 *  SE MANDA A GUARDAR MEDIANTE EL MODELO*/
		
		List<Usuario> usuarios=usuarioModel.getUsuarios();
		return usuarios;
	}

	@Override
	public int destroyUsuario(int id) {
		// TODO Auto-generated method stub
		int resultado= usuarioModel.destroyUsuario(id);
		
		return resultado;
	}

	
}
