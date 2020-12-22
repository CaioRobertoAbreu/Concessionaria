package br.com.caio.concessionaria.models;

public enum Status {

    AGENDAMENTO_NAO_REALIZADO(100, "Veiculo aguardando agendamento pelo usuario"),
    EM_AGENDAMANENTO(200, "Inspeção agendada"),
    RECUSADO(300, "Lamentamos, mas não temos interesse pela compra deste veiculo"),
    ENTREGA_DOCUMENTACAO_AGENDADA(400, "Entrega de documentação agendada"),
    COMPRA_REALIZADA(500, "Parabéns! Compramos o seu veículo"),
    DESISTENCIA(600, "Desistência de venda");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static Status toEnum(Integer codigo){
        if(codigo == null) {
            return null;
        }

        for(Status status : Status.values()){
            if(status.getCodigo().equals(codigo)){
                return status;
            }
        }

        throw new IllegalArgumentException("Codigo inválido");
    }
}
