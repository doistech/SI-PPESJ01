package br.com.doistech.ecclesiasystem.service

import br.com.doistech.ecclesiasystem.domain.Dizimo
import br.com.doistech.ecclesiasystem.domain.Igreja
import grails.transaction.Transactional
import org.hibernate.FetchMode

@Transactional
class DizimoService {

    def getDizimistas(Igreja comunidade = null){
        return Dizimo.createCriteria().list {
            if(comunidade != null){
                eq('comunidade', comunidade)
            }
            fetchMode('dizimista', FetchMode.JOIN)
            fetchMode('comunidade', FetchMode.JOIN)
        }
    }
}
