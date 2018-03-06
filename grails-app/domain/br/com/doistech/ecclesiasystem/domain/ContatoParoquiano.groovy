package br.com.doistech.ecclesiasystem.domain

class ContatoParoquiano {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Contato contato
    Paroquiano paroquiano
    static constraints = {
    }
}
