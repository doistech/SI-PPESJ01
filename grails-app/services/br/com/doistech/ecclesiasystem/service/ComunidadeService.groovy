package br.com.doistech.ecclesiasystem.service

import br.com.doistech.ecclesiasystem.domain.Igreja
import grails.transaction.Transactional

@Transactional
class ComunidadeService {

    def getComunidades(){
        Igreja paroquia = Igreja.createCriteria().get {eq('id', 1.toLong())}
        return Igreja.createCriteria().list {
            eq('paroquia',paroquia)
            eq('tipo', 'COM')
        }
    }
}
