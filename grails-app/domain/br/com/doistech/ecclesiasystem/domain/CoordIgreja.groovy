package br.com.doistech.ecclesiasystem.domain

class CoordIgreja {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Paroquiano coordenador
    Igreja igreja

    static constraints = {
    }
}
