package br.com.doistech.ecclesiasystem.domain

import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter

class Banner {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String nome
    Boolean tipoBanner
    String url
    String link
    Date dataInicio
    Date dataFim

    static constraints = {
        nome nullable: true, size: 1..100
        tipoBanner nullable: false
        url nullable: true
        link nullable: true
        dataInicio nullable: true
        dataFim nullable: true
    }
}
