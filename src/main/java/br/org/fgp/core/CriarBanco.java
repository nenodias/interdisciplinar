package br.org.fgp.core;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Contato;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Funcionario;
import br.org.fgp.model.FuncionarioTelefone;
import br.org.fgp.model.ItensPedido;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Pais;
import br.org.fgp.model.Pedido;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Telefone;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;

public class CriarBanco {
	
	private static final String JDBC_PASSWORD = "jdbc.password";
	private static final String JDBC_USERNAME = "jdbc.username";
	private static final String JDBC_URL = "jdbc.url";
	private static final String JDBC_DRIVER = "jdbc.driver";
	private static final String JDBC_DIALECT = "jdbc.dialect";
	private static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";
	private static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
	private static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "hibernate.connection.driver_class";
	private static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
	private static final String HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String HIBERNATE_UPDATE = "hbm2ddl.auto";
	private static Logger LOGGER = Logger.getLogger(CriarBanco.class);

	public static void main(String[] args) throws IOException {
		BasicDataSource datasource = ApplicationContextConfig.getContext().getBean(org.apache.commons.dbcp.BasicDataSource.class);
		Configuration configuracao = new Configuration();
		Properties properties = new Properties();
		properties.load( ApplicationContextConfig.getContext().getClassLoader().getResourceAsStream("hibernate.properties") );
		
		String dialect = properties.getProperty(JDBC_DIALECT);
		String driver = properties.getProperty(JDBC_DRIVER);
		String url = properties.getProperty(JDBC_URL);
		String username = properties.getProperty(JDBC_USERNAME);
		String password = properties.getProperty(JDBC_PASSWORD);
		
		properties.setProperty(HIBERNATE_UPDATE, Boolean.TRUE.toString());
		properties.setProperty(HIBERNATE_DIALECT, dialect);
		properties.setProperty(HIBERNATE_CONNECTION_URL, url);
		properties.setProperty(HIBERNATE_CONNECTION_DRIVER_CLASS, driver);
		properties.setProperty(HIBERNATE_CONNECTION_USERNAME, username);
		properties.setProperty(HIBERNATE_CONNECTION_PASSWORD, password);
		
		configuracao.setProperties(properties);
		
		configuracao.addAnnotatedClass(Categoria.class);
		configuracao.addAnnotatedClass(Cidade.class);
		configuracao.addAnnotatedClass(Contato.class);
		configuracao.addAnnotatedClass(ContatoFornecedor.class);
		configuracao.addAnnotatedClass(ContatoTelefone.class);
		configuracao.addAnnotatedClass(EntradaProduto.class);
		configuracao.addAnnotatedClass(Estado.class);
		configuracao.addAnnotatedClass(Fornecedor.class);
		configuracao.addAnnotatedClass(Funcionario.class);
		configuracao.addAnnotatedClass(FuncionarioTelefone.class);
		configuracao.addAnnotatedClass(ItensPedido.class);
		configuracao.addAnnotatedClass(Marca.class);
		configuracao.addAnnotatedClass(Pais.class);
		configuracao.addAnnotatedClass(Pedido.class);
		configuracao.addAnnotatedClass(Produto.class);
		configuracao.addAnnotatedClass(Setor.class);
		configuracao.addAnnotatedClass(Telefone.class);
		configuracao.addAnnotatedClass(Usuario.class);
		
		try{
			SchemaExport schema = new SchemaExport(configuracao,datasource.getConnection());
			schema.create(true, true);
			
			Usuario administrador = new Usuario();
			administrador.setLogin("admin");
			administrador.setSenha(SecurityUtils.encrypt("123"));
			administrador.setTipo(TipoUsuario.ADMINISTRADOR);
			
			UsuarioDao usuarioDao = ApplicationContextConfig.getContext().getBean(UsuarioDao.class);
			usuarioDao.salvar(administrador);
			
		}catch(Exception e){
			LOGGER.error(e);
		}
		
	}
}
