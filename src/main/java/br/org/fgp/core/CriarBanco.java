package br.org.fgp.core;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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

public class CriarBanco {
	
	private static Logger LOGGER = Logger.getLogger(CriarBanco.class);

	public static void main(String[] args) throws IOException {
		BasicDataSource datasource = ApplicationContextConfig.getContext().getBean(org.apache.commons.dbcp.BasicDataSource.class);
		Configuration configuracao = new Configuration();
		Properties properties = new Properties();
		properties.load( ApplicationContextConfig.getContext().getClassLoader().getResourceAsStream("hibernate.properties") );
		
		String dialect = properties.getProperty("hibernate.connection.dialect");
		
		properties.setProperty("hbm2ddl.auto", Boolean.TRUE.toString());
		properties.setProperty("hibernate.dialect", dialect);
		
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
		}catch(Exception e){
			LOGGER.error(e);
		}
		
	}
	
}
