package br.com.doistech.ecclesiasystem.domain

class IgrejaPastoral {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Pastoral pastoral
    Igreja igreja
    Paroquiano coordenador

    static constraints = {
    }
}
