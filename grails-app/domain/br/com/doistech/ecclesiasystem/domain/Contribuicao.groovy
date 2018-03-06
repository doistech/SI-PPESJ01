package br.com.doistech.ecclesiasystem.domain

class Contribuicao {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String tipo
    Date dataReferencia
    Date dataApuracao
    Integer ano
    Double valor
    Paroquiano contribuinte

    static constraints = {
        tipo nullable: true
        dataReferencia nullable: true
        dataApuracao nullable: true
        ano nullable: true
        valor nullable: true
        contribuinte nullable: true
    }

}
