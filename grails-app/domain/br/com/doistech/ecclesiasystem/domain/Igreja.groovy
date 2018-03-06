package br.com.doistech.ecclesiasystem.domain

class Igreja {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String nome
    String tipo

    Igreja paroquia

    static constraints = {
    }
}
