package mx.com.aforecoppel.WS_Movies_CRUD.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.aforecoppel.WS_Movies_CRUD.interfaces.servicio.IUsuarioService;
import mx.com.aforecoppel.WS_Movies_CRUD.objetos.Usuario;


@RestController
public class ControladorREST {
private static final Logger logger = Logger.getLogger(ControladorREST.class);
	
	@Autowired
	IUsuarioService usuarioService; /*SE INYECTA LA CAPA DE NEGOCIO*/

	@RequestMapping(value="/getUsuariosRest")
	public List<Usuario> getUsuariosREST(){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> getUsuariosREST();");
		
		List<Usuario> sRespuesta=new ArrayList<Usuario>();
		try {
			//SE OBTINE LA LISTA DE USUARIOS ACTUALIZADA
			sRespuesta = usuarioService.getUsuarios();
			logger.info(":: tallerSpring :: Consumo Del Metodo --> getUsuariosREST(); :: Datos: "+ sRespuesta.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> getUsuariosREST();");
		logger.info(":: tallerSpring ::");
		return sRespuesta;
	}
	// Servicio de insert
	@RequestMapping(value="/consumirInsertUsuario", method = RequestMethod.POST)
	public Map<String, Object> insertarUsuarioREST(@RequestBody Usuario usuario){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> insertarUsuarioREST();");
		Map<String,Object> map= new HashMap<String, Object>();
		String mensaje;
		int resultado = 0;

		try {			
			logger.info(":: tallerSpring ::insertarUsuarioREST(); Datos: " + usuario.toString());
			//SE ENVIA A GUARDAR MEDIANTE LA CAPA DE NEGOCIO
			resultado=usuarioService.saveUsuario(usuario);
			
			if(resultado>=1) {
				mensaje="El usuario se guardo correctamente";
			}else {
				mensaje="Ocurrió un error al guardar el usuario";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje="Ocurrió un error al guardar el usuario "+e.getMessage();
		}
		logger.info(":: tallerSpring :: Mapeo de datos --> insertarUsuarioREST();");
		map.put("mensaje",mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> insertarUsuarioREST();");
		//SE REGRESA EL MAP CON LA INFORMACION MOSTRAR
		logger.info(":: tallerSpring ::");
		return map;
	}
	@RequestMapping(value="/consumirDestroyUsuario",method = RequestMethod.POST)
	public Map<String, Object> consumirDestroyUsuario(@RequestBody int id){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> consumirDestroyUsuario();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		
		String mensaje = "";
		int resultado = 0;

		try {			
			//SE CREA OBJETO USUARIO PARA SER ENVIADO ENTRE LAS CAPAS
			Usuario usuario= new Usuario();
			usuario.setId(id);
			logger.info(":: tallerSpring :: consumirDestroyUsuario(); Datos: id = " + id);
			//SE ENVIA A Eliminar MEDIANTE LA CAPA DE NEGOCIO
			resultado=usuarioService.destroyUsuario(usuario.getId());
			
			if(resultado>=1) {
				mensaje="El usuario se elimino correctamente";
			}else {
				mensaje="Ocurrió un error al eliminar el usuario";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje="Ocurrió un error al eliminar el usuario "+e.getMessage();
		}
		//SE GUARDA LA INFORMACION A MANDAR A LA VISTA POR MEDIO DEL MAP
		map.put("mensaje", mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> consumirDestroyUsuario();");
		//SE REGRESA EL NOMBRE DE LA VISTA Y EL MAP CON LA INFORMACION MOSTRAR
		logger.info(":: tallerSpring ::");
		return map;
	}
	@RequestMapping(value="/consumirUpdateUsuario",method = RequestMethod.POST)
	public Map<String, Object> consumirUpdateUsuario(@RequestBody Usuario usuario){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> consumirUpdateUsuario();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		
		String mensaje = "";
		int resultado = 0;

		try {			
			logger.info(":: tallerSpring :: consumirUpdateUsuario(); Datos: " + usuario);
			//SE ENVIA A Eliminar MEDIANTE LA CAPA DE NEGOCIO
			resultado=usuarioService.updateUsuario(usuario);
			
			if(resultado>=1) {
				mensaje="El usuario se actualizo correctamente";
			}else {
				mensaje="Ocurrió un error al actualizar el usuario";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje="Ocurrió un error al actualizar el usuario "+e.getMessage();
		}
		//SE GUARDA LA INFORMACION A MANDAR A LA VISTA POR MEDIO DEL MAP
		map.put("mensaje", mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> consumirUpdateUsuario();");
		//SE REGRESA EL NOMBRE DE LA VISTA Y EL MAP CON LA INFORMACION MOSTRAR
		logger.info(":: tallerSpring ::");
		return map;
	}
	// Servicios Para PHP
	@RequestMapping(value="/altaUsuarioPHP")
	public Map<String, Object> altaUsuarioPHP(HttpServletRequest request){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> altaUsuarioREST();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String nss=request.getParameter("nss");
		String curp=request.getParameter("curp");
		String mensaje;
		int resultado = 0;
		
		try {			
			//SE CREA OBJETO USUARIO PARA SER ENVIADO ENTRE LAS CAPAS
			Usuario usuario= new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setNss(nss);
			usuario.setCurp(curp);
			
			logger.info(":: tallerSpring ::altaUsuarioREST(); Datos: " + usuario.toString());
			//SE ENVIA A GUARDAR MEDIANTE LA CAPA DE NEGOCIO
			resultado=usuarioService.saveUsuario(usuario);
			
			if(resultado>=1) {
				mensaje="El usuario se guardo correctamente";
			}else {
				mensaje="Ocurrió un error al guardar el usuario";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje="Ocurrió un error al guardar el usuario "+e.getMessage();
		}
		//SE GUARDA LA INFORMACION A MANDAR A LA VISTA POR MEDIO DEL MAP
		map.put("mensaje", mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> altaUsuarioREST();");
		logger.info(":: tallerSpring ::");
		
		return map;
	}
	@RequestMapping(value="/destroyUsuarioPHP")
	public Map<String, Object> destroyUsuarioPHP(HttpServletRequest request){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> destroyUsuarioPHP();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		
		/*SE OBTIENEN LOS DATOS DE LA VISTA CON EL REQUEST*/
		String id=request.getParameter("id");

		String mensaje;
		int resultado = 0;
		try {			
			Usuario usuario= new Usuario();
			usuario.setId(Integer.parseInt(id));
			
			logger.info(":: tallerSpring ::destroyUsuarioPHP(); Datos: " + usuario.getId());
			resultado=usuarioService.destroyUsuario(usuario.getId());
			
			if(resultado>=1) {
				mensaje="El usuario se elimino correctamente";
			}else {
				mensaje="Ocurrió un error al eliminar el usuario";

			}
		} catch (Exception e) {
			e.printStackTrace();
			mensaje="Ocurrió un error al eliminar el usuario "+e.getMessage();
		}
		map.put("mensaje", mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> destroyUsuarioPHP();");
		logger.info(":: tallerSpring ::");
		return map;
	}
	@RequestMapping(value="/editarUsuarioPHP")
	public Map<String, Object> editarUsuarioPHP(HttpServletRequest request){
		logger.info(":: tallerSpring ::");
		logger.info(":: tallerSpring :: Inicia Consumo Del Metodo --> editarUsuarioPHP();");
		
		Map<String,Object> map= new HashMap<String, Object>();
		
		String id=request.getParameter("id");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String nss=request.getParameter("nss");
		String curp=request.getParameter("curp");
		String mensaje;
		int resultado = 0;
		
		try {			
			//SE CREA OBJETO USUARIO PARA SER ENVIADO ENTRE LAS CAPAS
			Usuario usuario= new Usuario();
			usuario.setId(Integer.parseInt(id));
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setNss(nss);
			usuario.setCurp(curp);
			
			logger.info(":: tallerSpring ::editarUsuarioPHP(); Datos: " + usuario.toString());
			//SE ENVIA A GUARDAR MEDIANTE LA CAPA DE NEGOCIO
			resultado=usuarioService.updateUsuario(usuario);
			
			if(resultado>=1) {
				mensaje="La informacion del usuario se modifico correctamente";
			}else {
				mensaje="Ocurrió un error al modificar la informacion del usuario";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje="Ocurrió un error al modificar la informacion del usuario, Error: "+e.getMessage();
		}
		//SE GUARDA LA INFORMACION A MANDAR A LA VISTA POR MEDIO DEL MAP
		map.put("mensaje", mensaje);
		map.put("respuesta", resultado);
		logger.info(":: tallerSpring :: Fin Consumo Del Metodo --> editarUsuarioPHP();");
		logger.info(":: tallerSpring ::");
		
		return map;
	}
}
