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
@Table(name = "SETOR")
public class Setor {

    private static final int SETOR_MAX = 30;

    private static final String SETOR = "Setor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSetor")
    public Integer id;

    @NotBlank(message = MessagemUtil.CAMPO + SETOR + MessagemUtil.NOT_BLANK)
    @Length(max = SETOR_MAX, message = MessagemUtil.CAMPO + SETOR + MessagemUtil.MAX + SETOR_MAX)
    @Column(name = SETOR, length = SETOR_MAX)
    @LabelDescricao
    public String setor;

    @Pesquisa(nome = "Id", posicao = 0)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Pesquisa(nome = SETOR, posicao = 1)
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

}
