package br.org.fgp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.org.fgp.core.MessagemUtil;

@Embeddable
public class Endereco implements Serializable {

    private static final int BAIRRO_MAX = 30;

    private static final String BAIRRO = "Bairro";

    private static final int NUMERO_MAX = 10;

    private static final String NUMERO = "Numero";

    private static final int RUA_MAX = 50;

    private static final String RUA = "Rua";

    private static final long serialVersionUID = 3771961905394408023L;

    @NotBlank(message = MessagemUtil.CAMPO + RUA + MessagemUtil.NOT_BLANK)
    @Length(max = RUA_MAX, message = MessagemUtil.CAMPO + RUA + MessagemUtil.MAX + RUA_MAX)
    @Column(name = RUA, length = RUA_MAX)
    public String rua;

    @NotBlank(message = MessagemUtil.CAMPO + NUMERO + MessagemUtil.NOT_BLANK)
    @Length(max = NUMERO_MAX, message = MessagemUtil.CAMPO + NUMERO + MessagemUtil.MAX + NUMERO_MAX)
    @Column(name = NUMERO, length = NUMERO_MAX)
    public String numero;

    @NotBlank(message = MessagemUtil.CAMPO + BAIRRO + MessagemUtil.NOT_BLANK)
    @Length(max = BAIRRO_MAX, message = MessagemUtil.CAMPO + BAIRRO + MessagemUtil.MAX + BAIRRO_MAX)
    @Column(name = BAIRRO, length = BAIRRO_MAX)
    public String bairro;

    @ManyToOne
    @JoinColumn(name = "IdCidade")
    public Cidade cidade;

    public Endereco() {
        super();
    }

    public Endereco(String rua, String numero, String bairro, Cidade cidade) {
        super();
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
