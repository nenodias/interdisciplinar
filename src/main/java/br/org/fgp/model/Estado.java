package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "ESTADO", indexes = @Index(columnList = "IdPais"))
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEstado")
    public Integer id;

    @Column(name = "Estado", length = 40)
    public String estado;

    @ManyToOne
    @JoinColumn(name = "IdPais")
    public Pais pais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return estado;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder().append(id).append(estado);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Estado) {
            Estado other = (Estado) obj;
            EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.estado, other.estado);
            return builder.isEquals();
        }
        return false;
    }

}
