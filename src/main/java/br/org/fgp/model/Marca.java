package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.LabelDescricao;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "MARCA")
public class Marca {

    private static final int MARCA_MAX = 30;

    private static final String MARCA = "Marca";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMarca")
    public Integer id;

    @NotBlank(message = MessagemUtil.CAMPO + MARCA + MessagemUtil.NOT_BLANK)
    @Length(max = MARCA_MAX, message = MessagemUtil.CAMPO + MARCA + MessagemUtil.MAX + MARCA_MAX)
    @Column(name = MARCA, length = MARCA_MAX)
    @LabelDescricao
    public String marca;

    @Pesquisa(nome = "Id", posicao = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = MARCA, posicao = 1)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return marca;
    }

}
