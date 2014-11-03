package br.org.fgp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.org.fgp.core.MessagemUtil;

@Entity
@Table(name = "CONTATO", indexes = @Index(columnList = "IdSetor") )
public class Contato {

	private static final int EMAIL_MAX = 200;

	private static final String EMAIL = "Email";

	private static final String SETOR = "Setor";

	private static final int NOME_MAX = 200;

	private static final String NOME = "Nome";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@NotBlank(message = MessagemUtil.CAMPO + EMAIL + MessagemUtil.NOT_BLANK)
	@Length(max = EMAIL_MAX, message = MessagemUtil.CAMPO + EMAIL + MessagemUtil.MAX + EMAIL_MAX )
	@Column(name = EMAIL , length = EMAIL_MAX)
	public String email;
	
	@NotBlank(message = MessagemUtil.CAMPO + NOME + MessagemUtil.NOT_BLANK)
	@Length(max = NOME_MAX, message = MessagemUtil.CAMPO + NOME + MessagemUtil.MAX + NOME_MAX )
	@Column(name = NOME, length = NOME_MAX)
	public String nome;
	
	@NotNull(message = MessagemUtil.CAMPO + SETOR + MessagemUtil.NOT_BLANK)
	@ManyToOne
	@JoinColumn(name = "IdSetor")
	public Setor setor;
	
	@OneToMany(mappedBy = "contato")
	public List<ContatoTelefone> listaTelefone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	

	public List<ContatoTelefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<ContatoTelefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder().append(id).append(email).append(nome);
		return builder.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Contato){
			Contato other = (Contato)obj;
			EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.email, other.email).append(this.nome, other.nome);
			return builder.isEquals();
		}
		return false;
	}
	
}
