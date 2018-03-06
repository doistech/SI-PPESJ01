package br.com.doistech.ecclesiasystem.domain

class Endereco {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String estado
    String municipio
    String endereco
    String complemento
    Integer numero
    String bairro
    String subBairro
    String cep

    static constraints = {
        estado nullable: true
        municipio nullable: true
        endereco nullable: true
        complemento nullable: true
        numero nullable: true
        bairro nullable: true
        subBairro nullable: true
        cep nullable: true
    }
}
