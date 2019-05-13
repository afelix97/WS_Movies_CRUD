package mx.com.aforecoppel.WS_Movies_CRUD.interfaces.modelo;

import java.util.List;

import mx.com.aforecoppel.WS_Movies_CRUD.objetos.Usuario;



public interface IUsuarioModel {
	public List<Usuario> getUsuarios();
	public Usuario getUsuario(int id);
	public int updateUsuario(Usuario usuario);
	public int saveUsuario(Usuario usuario);
	public int destroyUsuario(int id);
}
