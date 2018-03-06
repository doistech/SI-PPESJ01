package br.com.doistech.ecclesiasystem.domain

class Dizimo {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Integer count
    String idComunidade
    String codigoCarteira
    Paroquiano dizimista
    Igreja comunidade

    static constraints = {
        count nullable: true
        idComunidade nullable: true
        codigoCarteira nullable: true
        dizimista nullable: true
        comunidade nullable: true
    }
}
