package br.org.fgp.model.enums;

public enum TipoUsuario {
	BALCAO(1, "Balcão"),
	ADMINISTRADOR(2, "Administrador");

	private TipoUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private Integer codigo;
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public static TipoUsuario generate(Integer codigo){
		for (TipoUsuario item : TipoUsuario.values()) {
			if(item.getCodigo().equals(codigo)){
				return item;
			}
		}
		return null;
	}
}
