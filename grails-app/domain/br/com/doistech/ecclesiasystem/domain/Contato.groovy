package br.com.doistech.ecclesiasystem.domain

class Contato {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String tipo
    String descricao
    String descricao1

    static constraints = {
        tipo nullable: true
        descricao nullable: true
        descricao1 nullable: true
    }
}
