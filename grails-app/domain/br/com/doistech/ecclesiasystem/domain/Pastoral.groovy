package br.com.doistech.ecclesiasystem.domain

class Pastoral {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String nome
    String descricao

    static constraints = {
    }
}
