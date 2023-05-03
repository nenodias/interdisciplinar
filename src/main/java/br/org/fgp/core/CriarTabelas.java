package br.org.fgp.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
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
import org.hibernate.tool.schema.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CriarTabelas {

    private static final String JDBC_PASSWORD = "jdbc.password";
    private static final String JDBC_USERNAME = "jdbc.username";
    private static final String JDBC_URL = "jdbc.url";
    private static final String JDBC_DRIVER = "jdbc.driver";
    private static final String JDBC_DIALECT = "jdbc.dialect";
    private static final Logger LOGGER = LoggerFactory.getLogger(CriarTabelas.class);

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(ApplicationContextConfig.getContext().getClassLoader().getResourceAsStream("hibernate.properties"));

        String dialect = properties.getProperty(JDBC_DIALECT);
        String driver = properties.getProperty(JDBC_DRIVER);
        String url = properties.getProperty(JDBC_URL);
        String username = properties.getProperty(JDBC_USERNAME);
        String password = properties.getProperty(JDBC_PASSWORD);

        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", driver);
        settings.put("hibernate.dialect", dialect);
        settings.put("hibernate.connection.url", url);
        settings.put("hibernate.connection.username", username);
        settings.put("hibernate.connection.password", password);
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadata = new MetadataSources(serviceRegistry);

        metadata.addAnnotatedClass(Categoria.class);
        metadata.addAnnotatedClass(Cidade.class);
        metadata.addAnnotatedClass(Contato.class);
        metadata.addAnnotatedClass(ContatoFornecedor.class);
        metadata.addAnnotatedClass(ContatoTelefone.class);
        metadata.addAnnotatedClass(EntradaProduto.class);
        metadata.addAnnotatedClass(Estado.class);
        metadata.addAnnotatedClass(Fornecedor.class);
        metadata.addAnnotatedClass(Endereco.class);
        metadata.addAnnotatedClass(UsuarioTelefone.class);
        metadata.addAnnotatedClass(VendaItem.class);
        metadata.addAnnotatedClass(Marca.class);
        metadata.addAnnotatedClass(Pais.class);
        metadata.addAnnotatedClass(Venda.class);
        metadata.addAnnotatedClass(Produto.class);
        metadata.addAnnotatedClass(Setor.class);
        metadata.addAnnotatedClass(Telefone.class);
        metadata.addAnnotatedClass(Usuario.class);

        try {
            EnumSet<TargetType> enumSet = EnumSet.of(TargetType.DATABASE);
            SchemaExport schemaExport = new SchemaExport();
            schemaExport.execute(enumSet, SchemaExport.Action.BOTH, metadata.buildMetadata());

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
                LOGGER.error(e.getMessage(), e);
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
            LOGGER.error(e.getMessage(), e);
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return stringBuilder.toString();
    }
}
