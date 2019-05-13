package mx.com.aforecoppel.WS_Movies_CRUD.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.com.aforecoppel.WS_Movies_CRUD.interfaces.modelo.IUsuarioModel;
import mx.com.aforecoppel.WS_Movies_CRUD.objetos.Usuario;



/*IMPORTANTE INDICAR QUE ESTA CLASE SERA LA CAPA DE PERSISTENCIA, SIN LA ETIQUETA @Repository NO SERA CANDIDATA A INYECTARSE EN LA CAPA DE NEGOCIO (@Service)*/
@Repository
public class UsuarioModel implements IUsuarioModel {

	/*SE INYECTA EL OBJETO CREADO DESDE EL CONTENEDOR DE CONFIGURACIONES DE SPRING, CUMPLIENDO CON EL PATRO MVC ES AQUI SOLAMENTE DONDE SE ACCEDE A BASE DE DATOS*/
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Usuario getUsuario(int id) throws DataAccessException {
		String sql = "SELECT * FROM cat_usuarios WHERE id=?";		
		
		/*SI SOLO SE QUEIRE OBTENER UN REGISTRO SE UTILIZA EL METODO queryForObject */
		/*PARA UTILIZAR EL OBJETO BeanPropertyRowMapper, SE TIENEN QUE MACHEAR LOS NOMBRES DEL POJO USUARIO CON LOS DE LA BASE DE DATOS*/
		Usuario usuario=jdbcTemplate.queryForObject(sql,new Object[]{id}, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		
		/*EN EL CASO QUE EL NOMBRADO DE LAS COLUMNAS EN BASE DATOS NO MACHEE CON LOS POJOS SE PUEDE UTILIZAR EL OBJETO RowMapper
		 * EN EL CUAL SOLO LE INDICAREMOS QUE DATOS DE LA BASE DE DATOS CORRESPONDE CON EL POJO*/
		Usuario usuario2=jdbcTemplate.queryForObject(sql,new Object[]{id}, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet datos, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				usuario.setNombre(datos.getString("nombre"));
				usuario.setApellido(datos.getString("apellido"));
				usuario.setNss(datos.getString("nss"));
				return usuario;
			}
		});
		
		return usuario;
	}

	@Override
	public int updateUsuario(Usuario usuario) {
		int resultado = 0;
		String sql = "UPDATE usuarios SET nombre = ?,apellido = ?, nss = ?, curp = ? WHERE id = ?";
		
		//PARA SENTENCIAS DE INSERT, DELETE, UPDATE SE UTILIZA EL METODO update del jdbcTemplate
		resultado = jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getNss(), usuario.getCurp(), usuario.getId());

		
		return resultado;
	}

	@Override
	public int saveUsuario(Usuario usuario)throws DataAccessException {
		
		int resultado = 0;
		String sql = "INSERT INTO usuarios (nombre,apellido,nss,curp) VALUES (?,?,?,?)";
		
		//PARA SENTENCIAS DE INSERT, DELETE, UPDATE SE UTILIZA EL METODO update del jdbcTemplate
		resultado = jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getNss(), usuario.getCurp());

		
		return resultado;
	}

	@Override
	public List<Usuario> getUsuarios()throws DataAccessException  {
		String sql = "SELECT * FROM usuarios order by id;";
		
		/*PARA UTILIZAR EL OBJETO BeanPropertyRowMapper, SE TIENEN QUE MACHEAR LOS NOMBRES DEL POJO USUARIO CON LOS DE LA BASE DE DATOS*/
		List<Usuario> usuarios= jdbcTemplate.query(sql, new BeanPropertyRowMapper<Usuario>(Usuario.class));
		
		/*EN EL CASO QUE EL NOMBRADO DE LAS COLUMNAS EN BASE DATOS NO MACHEE CON LOS POJOS SE PUEDE UTILIZAR EL OBJETO RowMapper
		 * EN EL CUAL SOLO LE INDICAREMOS QUE DATOS DE LA BASE DE DATOS CORRESPONDE CON EL POJO*/
		/*List<Usuario> usuarios2=jdbcTemplate.query(sql, new RowMapper<Usuario>() {
			@Override
			public Usuario mapRow(ResultSet datos, int rowNum) throws SQLException {
				Usuario usuario = new Usuario();
				usuario.setId(datos.getInt("id"));
				usuario.setNombre(datos.getString("nombre"));
				return usuario;
			}});*/
		
		return usuarios;
	}

	@Override
	public int destroyUsuario(int id) {
		int resultado = 0;
		String sql = "DELETE FROM usuarios WHERE id = ? ;";
		
		resultado = jdbcTemplate.update(sql, id);
		
		return resultado;
	}

}
