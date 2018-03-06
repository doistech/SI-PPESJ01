package br.com.doistech.ecclesiasystem.domain

class Permissao {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String nome


    static constraints = {
        nome nullable: false
    }

}
