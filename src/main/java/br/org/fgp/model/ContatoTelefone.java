package br.org.fgp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import jakarta.validation.Valid;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "CONTATO_TELEFONE", indexes = {@Index(columnList = "IdContato"), @Index(columnList = "IdTelefone")}, uniqueConstraints = @UniqueConstraint(columnNames = {"IdContato", "IdTelefone"}))
public class ContatoTelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "IdContato", nullable = false)
    public Contato contato;

    @Valid
    @ManyToOne
    @JoinColumn(name = "IdTelefone", nullable = false)
    public Telefone telefone;

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Pesquisa(nome = "Telefone", posicao = 0)
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder().append(id).append(contato).append(telefone);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ContatoTelefone) {
            ContatoTelefone other = (ContatoTelefone) obj;
            EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.contato, other.contato).append(this.telefone, other.telefone);
            return builder.isEquals();
        }
        return false;
    }

}
