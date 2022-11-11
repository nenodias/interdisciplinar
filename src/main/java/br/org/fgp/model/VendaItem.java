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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "VENDA_ITEM", indexes = {@Index(columnList = "IdProduto"), @Index(columnList = "IdVenda")})
public class VendaItem {

    private static final int VALOR_MAX = 15;

    private static final String VALOR = "Valor Unitário";

    private static final int VALOR_FRACAO = 2;

    private static final int VALOR_INT = 13;

    private static final String PRODUTO = "Produto";

    private static final String QUANTIDADE = "Quantidade";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVendaItem")
    public Integer id;

    @NotNull(message = MessagemUtil.CAMPO + QUANTIDADE + MessagemUtil.NOT_BLANK)
    @Column(name = QUANTIDADE)
    public Integer quantidade;

    @Digits(integer = VALOR_INT, fraction = VALOR_FRACAO, message = MessagemUtil.CAMPO + VALOR + MessagemUtil.DIGITS + VALOR_MAX + MessagemUtil.INTEIROS + MessagemUtil.AND + VALOR_FRACAO + MessagemUtil.CASAS_DECIMAIS)
    @Column(name = "ValorUnitario", scale = VALOR_FRACAO, precision = VALOR_MAX)
    public BigDecimal valorUnitario;

    @NotNull(message = MessagemUtil.CAMPO + PRODUTO + MessagemUtil.NOT_BLANK)
    @ManyToOne
    @JoinColumn(name = "IdProduto")
    public Produto produto;

    @ManyToOne
    @JoinColumn(name = "IdVenda")
    public Venda venda;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = "Quantidade", posicao = 1)
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Pesquisa(nome = "Valor Unitário", posicao = 2)
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Pesquisa(nome = "Produto", posicao = 0)
    public String getProdutoTexto() {
        return produto.getNome();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
