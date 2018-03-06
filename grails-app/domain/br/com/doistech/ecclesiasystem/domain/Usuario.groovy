package br.com.doistech.ecclesiasystem.domain

class Usuario {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String usuario
    String senha

    static constraints = {
    }
}
