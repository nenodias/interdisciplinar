package br.org.fgp.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.org.fgp.dao.CidadeDao;
import br.org.fgp.dao.PaisDao;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Categoria;
import br.org.fgp.model.Cidade;
import br.org.fgp.model.Contato;
import br.org.fgp.model.ContatoFornecedor;
import br.org.fgp.model.ContatoTelefone;
import br.org.fgp.model.Endereco;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Estado;
import br.org.fgp.model.Fornecedor;
import br.org.fgp.model.Marca;
import br.org.fgp.model.Pais;
import br.org.fgp.model.Produto;
import br.org.fgp.model.Setor;
import br.org.fgp.model.Telefone;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.UsuarioTelefone;
import br.org.fgp.model.Venda;
import br.org.fgp.model.VendaItem;
import br.org.fgp.model.enums.TipoUsuario;

public class CriarTabelas {

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
    private static Logger LOGGER = Logger.getLogger(CriarTabelas.class);

    public static void main(String[] args) throws IOException {
        BasicDataSource datasource = ApplicationContextConfig.getContext().getBean(org.apache.commons.dbcp.BasicDataSource.class);
        Configuration configuracao = new Configuration();
        Properties properties = new Properties();
        properties.load(ApplicationContextConfig.getContext().getClassLoader().getResourceAsStream("hibernate.properties"));

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
        configuracao.addAnnotatedClass(Endereco.class);
        configuracao.addAnnotatedClass(UsuarioTelefone.class);
        configuracao.addAnnotatedClass(VendaItem.class);
        configuracao.addAnnotatedClass(Marca.class);
        configuracao.addAnnotatedClass(Pais.class);
        configuracao.addAnnotatedClass(Venda.class);
        configuracao.addAnnotatedClass(Produto.class);
        configuracao.addAnnotatedClass(Setor.class);
        configuracao.addAnnotatedClass(Telefone.class);
        configuracao.addAnnotatedClass(Usuario.class);

        try {
            SchemaExport schema = new SchemaExport(configuracao, datasource.getConnection());
            schema.create(true, true);

            PaisDao paisDao = ApplicationContextConfig.getContext().getBean(PaisDao.class);
            paisDao.execute("INSERT INTO PAIS(Pais) VALUES('Brasil');");

            paisDao.execute("INSERT INTO ESTADO(Estado, IdPais) VALUES " +
                    "('Acre', 1),                                    "
                    + "('Alagoas',1),                                "
                    + "('Amazonas', 1),                              "
                    + "('Amapá', 1),                                 "
                    + "('Bahia', 1),                                 "
                    + "('Ceará', 1),                                 "
                    + "('Distrito Federal', 1),                      "
                    + "('Espírito Santo', 1),                        "
                    + "('Goiás', 1),                                 "
                    + "('Maranhão', 1),                              "
                    + "('Minas Gerais', 1),                          "
                    + "('Mato Grosso do Sul', 1),                    "
                    + "('Mato Grosso', 1),                           "
                    + "('Pará', 1),                                  "
                    + "('Paraíba', 1),                               "
                    + "('Pernambuco', 1),                            "
                    + "('Piauí', 1),                                 "
                    + "('Paraná', 1),                                "
                    + "('Rio de Janeiro', 1),                        "
                    + "('Rio Grande do Norte', 1),                   "
                    + "('Rondônia', 1),                              "
                    + "('Roraima', 1),                               "
                    + "('Rio Grande do Sul', 1),                     "
                    + "('Santa Catarina', 1),                        "
                    + "('Sergipe', 1),                               "
                    + "('São Paulo', 1),                             "
                    + "('Tocantins', 1);");
            CidadeDao cidadeDao = ApplicationContextConfig.getContext().getBean(CidadeDao.class);
            try {
                Arrays.stream(lerArquivo("script/cidades.sql")
                                .replaceAll("\n", "")
                                .split(";"))
                        .filter(StringUtils::isNotBlank)
                        .forEach(cidadeDao::execute);
            } catch (Exception e) {
                LOGGER.error(e);
            }

            UsuarioDao usuarioDao = ApplicationContextConfig.getContext().getBean(UsuarioDao.class);

            Usuario administrador = new Usuario();
            administrador.setLogin("admin");
            administrador.setSenha(SecurityUtils.encrypt("123"));
            administrador.setNome("Administrador");
            administrador.setNome("Administrador");
            administrador.setTipo(TipoUsuario.ADMINISTRADOR);
            administrador.setCpf("111.444.777-35");

            Cidade cidade = cidadeDao.buscarPorId(5010);

            administrador.setEndereco(new Endereco("Rua dos Bobos", "0", "Núcleo Habitacional Luiz Zillo", cidade));

            usuarioDao.salvar(administrador);
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    @SuppressWarnings("resource")
    public static String lerArquivo(String caminhoArquivo) throws URISyntaxException {
        StringBuilder stringBuilder = new StringBuilder("");
        URL resource = ApplicationContextConfig.getContext().getClassLoader().getResource(caminhoArquivo);
        File arquivo = new File(resource.toURI());
        if (arquivo.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                String line = null;
                String ls = System.getProperty("line.separator");

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return stringBuilder.toString();
    }
}
