package br.com.doistech.ecclesiasystem.domain

class AptidaoParoquiano {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Aptidao aptidao
    Paroquiano paroquiano

    static constraints = {
    }
}
