package br.org.fgp.model.enums;

public enum TipoTelefone {
    CELULAR(0, "Celular"),
    RESIDENCIAL(1, "Residencial"),
    COMERCIAL(2, "Comercial");

    private TipoTelefone(Integer codigo, String descricao) {
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

    public static TipoTelefone generate(Integer codigo) {
        for (TipoTelefone item : TipoTelefone.values()) {
            if (item.getCodigo().equals(codigo)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
