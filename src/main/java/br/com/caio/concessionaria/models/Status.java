package br.com.caio.concessionaria.models;

public enum Status {

    PRE_APROVADO(100, "Veículo pré-aprovado para venda"),
    EM_AGENDAMANENTO(200, "Inspeção agendada"),
    APROVADO(300, "Veículo aprovado para venda"),
    RECUSADO(400, "Veículo não aprovado para venda"),
    ENTREGA_DOCUMENTACAO_AGENDADA(500, "Agendamento de entrega de documentacao"),
    VENDA_REALIZADA(600, "Venda realizada para concessionária"),
    DESISTENCIA(700, "Desistência de venda");

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
