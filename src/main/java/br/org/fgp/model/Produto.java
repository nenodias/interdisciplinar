package br.org.fgp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.LabelDescricao;
import br.org.fgp.view.annotations.Pesquisa;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PRODUTO", indexes = {@Index(columnList = "IdCategoria"), @Index(columnList = "IdMarca")})
public class Produto {

    private static final String ESTOQUE_MINIMO = "Estoque Mínimo";

    private static final String ESTOQUE_MAXIMO = "Estoque Máximo";

    private static final String MARCA = "Marca";

    private static final String CATEGORIA = "Categoria";

    private static final String PRECO = "Preço";

    private static final int PRECO_FRACAO = 2;

    private static final int PRECO_INT = 13;

    private static final int PRECO_MAX = 15;

    private static final String DESCRICAO = "Descrição";

    private static final int DESCRICAO_MAX = 200;

    private static final int NOME_MAX = 50;

    private static final String NOME = "Nome";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProduto")
    public Integer id;

    @NotBlank(message = MessagemUtil.CAMPO + NOME + MessagemUtil.NOT_BLANK)
    @Size(max = NOME_MAX, message = MessagemUtil.CAMPO + NOME + MessagemUtil.MAX + NOME_MAX)
    @Column(name = NOME, length = NOME_MAX)
    @LabelDescricao
    public String nome;

    @NotBlank(message = MessagemUtil.CAMPO + DESCRICAO + MessagemUtil.NOT_BLANK)
    @Size(max = DESCRICAO_MAX, message = MessagemUtil.CAMPO + DESCRICAO + MessagemUtil.MAX + DESCRICAO_MAX)
    @Column(name = "Descricao", length = DESCRICAO_MAX)
    public String descricao;

    @Digits(integer = PRECO_INT, fraction = PRECO_FRACAO, message = MessagemUtil.CAMPO + PRECO + MessagemUtil.DIGITS + PRECO_MAX + MessagemUtil.INTEIROS + MessagemUtil.AND + PRECO_FRACAO + MessagemUtil.CASAS_DECIMAIS)
    @Column(name = "PrecoUnitario", scale = PRECO_FRACAO, precision = PRECO_MAX)
    public BigDecimal precoUnitario;

    @NotNull(message = MessagemUtil.CAMPO + ESTOQUE_MAXIMO + MessagemUtil.NOT_BLANK)
    @Column(name = "EstoqueMaximo")
    public Integer estoqueMaximo = 0;

    @NotNull(message = MessagemUtil.CAMPO + ESTOQUE_MINIMO + MessagemUtil.NOT_BLANK)
    @Column(name = "EstoqueMinimo")
    public Integer estoqueMinimo = 0;

    @Column(name = "EstoqueAtual")
    public Integer estoqueAtual = 0;

    @NotNull(message = MessagemUtil.CAMPO + CATEGORIA + MessagemUtil.NOT_BLANK)
    @ManyToOne
    @JoinColumn(name = "IdCategoria")
    public Categoria categoria;

    @NotNull(message = MessagemUtil.CAMPO + MARCA + MessagemUtil.NOT_BLANK)
    @ManyToOne
    @JoinColumn(name = "IdMarca")
    public Marca marca;

    @Pesquisa(nome = "Id", posicao = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = NOME, posicao = 1)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Pesquisa(nome = DESCRICAO, posicao = 2)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Pesquisa(nome = PRECO, posicao = 3)
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(Integer estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    @Pesquisa(nome = "Estoque", posicao = 4)
    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    @Pesquisa(nome = CATEGORIA, posicao = 5)
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Pesquisa(nome = MARCA, posicao = 6)
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
