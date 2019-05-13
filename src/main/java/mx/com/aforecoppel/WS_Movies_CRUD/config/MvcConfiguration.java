package mx.com.aforecoppel.WS_Movies_CRUD.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="mx.com.aforecoppel.WS_Movies_CRUD")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

/*SE CREA BEANES EN EL CONTENEDOR PRINCIPAL DE SPRING(IOC) LISTOS PARA SER CONTEMPLADOS PARA INYECTARSE*/
	
	/*BEAN PARA CREAR DATASOURCE COMO SU NOMBRE LO INDICA ES PARA SABER LA CONEXION A PERSISTENCIA*/
	/*@Bean 
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver"); //paquete donde se encuentra la clase del driver
		dataSource.setUrl("jdbc:postgresql://10.44.172.234:5432/aforeglobal");//URL DE CONEXION JDBC ESTA LA OBTIENEN DESDE LA DOCUMENTACION DE CADA MANEJADOR DE BASE DE DATOS
		dataSource.setUsername("sysaforeglobal"); //USUARIO DE LA BASE DE DATOS
		dataSource.setPassword("a2901ba525795f1fd311642f98c9f160");//CONTRASENA DE LA BASE DE DATOS
		
		return dataSource;
	}*/
	/*BEAN PARA CREAR DATASOURCE COMO SU NOMBRE LO INDICA ES PARA SABER LA CONEXION A PERSISTENCIA*/
	@Bean 
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //paquete donde se encuentra la clase del driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/prueba?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");//URL DE CONEXION JDBC ESTA LA OBTIENEN DESDE LA DOCUMENTACION DE CADA MANEJADOR DE BASE DE DATOS
		dataSource.setUsername("root"); //USUARIO DE LA BASE DE DATOS
		dataSource.setPassword("");//CONTRASENA DE LA BASE DE DATOS
		
		return dataSource;
	}

	/*BEAN PARA CREAR JDBCTEMPLATE Y SIMPLIFICAR EL USO DEL DATASOURCE Y TENER ACCESO A DATOS*/
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
}
