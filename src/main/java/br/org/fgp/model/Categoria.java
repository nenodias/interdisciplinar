package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.LabelDescricao;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    private static final int CATEGORIA_MAX = 30;

    private static final String CATEGORIA = "Categoria";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    public Integer id;

    @NotBlank(message = MessagemUtil.CAMPO + CATEGORIA + MessagemUtil.NOT_BLANK)
    @Size(max = CATEGORIA_MAX, message = MessagemUtil.CAMPO + CATEGORIA + MessagemUtil.MAX + CATEGORIA_MAX)
    @Column(name = CATEGORIA, length = CATEGORIA_MAX)
    @LabelDescricao
    public String descricao;

    @Pesquisa(nome = "Id", posicao = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = CATEGORIA, posicao = 1)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder().append(id).append(descricao);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Categoria) {
            Categoria other = (Categoria) obj;
            EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.descricao, other.descricao);
            return builder.isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
