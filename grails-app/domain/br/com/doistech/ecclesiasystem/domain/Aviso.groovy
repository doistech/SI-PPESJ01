package br.com.doistech.ecclesiasystem.domain

class Aviso {
    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    Date dataInicioVigencia
    Date dataFimVigencia
    Date dataEvento
    String hora
    String titulo
    String autor
    String descricao

    static constraints = {
        descricao(size: 1..400)
        autor(size: 1..100)
        titulo(size: 1..150)
        autor(nullable:true)
    }
}
