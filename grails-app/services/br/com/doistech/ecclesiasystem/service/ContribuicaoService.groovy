package br.com.doistech.ecclesiasystem.service

import br.com.doistech.ecclesiasystem.domain.Contribuicao
import br.com.doistech.ecclesiasystem.domain.Paroquiano
import grails.transaction.Transactional

import java.text.SimpleDateFormat

@Transactional
class ContribuicaoService {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

    def getContribuicao(Paroquiano paroquiano, Integer ano) {
        return Contribuicao.createCriteria().list {
            eq('contribuinte', paroquiano)
            eq('tipo', 'DIZ')
            eq('ano',ano)
        }
    }

    def getMesContribuicao(Integer ano, Integer mes, Integer dia, Paroquiano paroquiano) {
        Contribuicao contribuicao = new Contribuicao()
        List<Contribuicao> ContribuicaoList = new ArrayList<Contribuicao>()
        Calendar calendar = new GregorianCalendar(ano, mes - 2, dia)

        int count = 1
        for (Integer i = 0; i < 12; i++) {
            contribuicao = new Contribuicao()
            int num = count
            count++
            calendar.add(GregorianCalendar.MONTH, 1)
            calendar.set(GregorianCalendar.DAY_OF_MONTH, 1)
            contribuicao.dataReferencia = calendar.getTime()
            contribuicao.ano = ano
            contribuicao.tipo = 'DIZ'
            contribuicao.valor = 0.0
            contribuicao.contribuinte = paroquiano
            ContribuicaoList.add(contribuicao)
        }
        contribuicao = new Contribuicao()
        contribuicao.dataReferencia = dateFormat.parse(ano.toString()+'-12-31 23:59:59')
        contribuicao.ano = ano
        contribuicao.tipo = 'DIZ'
        contribuicao.valor = 0.0
        contribuicao.contribuinte = paroquiano
        ContribuicaoList.add(contribuicao)

        ContribuicaoList.each { Contribuicao cont ->
            cont.save(flush:true)
        }
    }

    public void saveContribuicao(Contribuicao contribuicao){
        contribuicao.save(flush:true)
    }
}
