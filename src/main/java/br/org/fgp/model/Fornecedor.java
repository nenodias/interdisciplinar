package br.org.fgp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.LabelDescricao;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "FORNECEDOR", indexes = @Index(columnList = "IdCidade"))
public class Fornecedor {

    private static final String RAZAO_SOCIAL = "Razao Social";

    private static final int RAZAO_SOCIAL_MAX = 200;

    private static final String NOME_FANTASIA = "Nome Fantasia";

    private static final int NOME_FANTASIA_MAX = 200;

    private static final int INSCRICAO_ESTADUAL_MAX = 20;

    private static final String CNPJ = "Cnpj";

    private static final int CNPJ_MAX = 20;

    private static final String INSCRICAO_ESTADUAL = "Inscrição Estadual";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFornecedor")
    public Integer id;

    @NotBlank(message = MessagemUtil.CAMPO + CNPJ + MessagemUtil.NOT_BLANK)
    @Size(max = CNPJ_MAX, message = MessagemUtil.CAMPO + CNPJ + MessagemUtil.MAX + CNPJ_MAX)
    @Column(name = CNPJ, length = CNPJ_MAX)
    public String cnpj;

    @NotBlank(message = MessagemUtil.CAMPO + INSCRICAO_ESTADUAL + MessagemUtil.NOT_BLANK)
    @Size(max = INSCRICAO_ESTADUAL_MAX, message = MessagemUtil.CAMPO + INSCRICAO_ESTADUAL + MessagemUtil.MAX + INSCRICAO_ESTADUAL_MAX)
    @Column(name = "IncricaoEstadual", length = INSCRICAO_ESTADUAL_MAX)
    public String inscricaoEstadual;

    @NotBlank(message = MessagemUtil.CAMPO + NOME_FANTASIA + MessagemUtil.NOT_BLANK)
    @Size(max = NOME_FANTASIA_MAX, message = MessagemUtil.CAMPO + NOME_FANTASIA + MessagemUtil.MAX + NOME_FANTASIA_MAX)
    @Column(name = "NomeFantasia", length = NOME_FANTASIA_MAX)
    @LabelDescricao
    public String nomeFantasia;

    @NotBlank(message = MessagemUtil.CAMPO + RAZAO_SOCIAL + MessagemUtil.NOT_BLANK)
    @Size(max = RAZAO_SOCIAL_MAX, message = MessagemUtil.CAMPO + RAZAO_SOCIAL + MessagemUtil.MAX + RAZAO_SOCIAL_MAX)
    @Column(name = "RazaoSocial", length = RAZAO_SOCIAL_MAX)
    public String razaoSocial;

    @Valid
    @Embedded
    public Endereco enderecoComercial;

    @Valid
    @OneToMany(mappedBy = "fornecedor")
    public List<ContatoFornecedor> listaContato;

    @Pesquisa(nome = "Código", posicao = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = "Cnpj", posicao = 3)
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Pesquisa(nome = "Código", posicao = 1)
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Pesquisa(nome = "Código", posicao = 2)
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Endereco getEnderecoComercial() {
        return enderecoComercial;
    }

    public void setEnderecoComercial(Endereco enderecoComercial) {
        this.enderecoComercial = enderecoComercial;
    }

    public List<ContatoFornecedor> getListaContato() {
        return listaContato;
    }

    public void setListaContato(List<ContatoFornecedor> listaContato) {
        this.listaContato = listaContato;
    }


}
