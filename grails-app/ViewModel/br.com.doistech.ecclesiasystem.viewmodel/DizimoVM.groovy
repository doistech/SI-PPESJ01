package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.domain.Contribuicao
import br.com.doistech.ecclesiasystem.domain.Dizimo
import br.com.doistech.ecclesiasystem.domain.Igreja
import br.com.doistech.ecclesiasystem.domain.Paroquiano
import br.com.doistech.ecclesiasystem.domain.Usuario
import br.com.doistech.ecclesiasystem.service.LoginService
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.zhtml.Messagebox
import br.com.doistech.ecclesiasystem.service.ComunidadeService
import br.com.doistech.ecclesiasystem.service.ContribuicaoService
import br.com.doistech.ecclesiasystem.service.DizimoService
import br.com.doistech.ecclesiasystem.service.InjectUtils
import org.zkoss.zk.ui.Session
import org.zkoss.zk.ui.Sessions

import java.text.SimpleDateFormat

class DizimoVM {
    SimpleDateFormat year = new SimpleDateFormat("yyyy")

    ContribuicaoService contribuicaoService
    ComunidadeService comunidadeService
    DizimoService dizimoService
    LoginService loginService

    List<Contribuicao> contribuicaoList = new ArrayList<Contribuicao>()
    List<Igreja> comunidadesList = new ArrayList<Igreja>()
    List<Dizimo> dizimistaList = new ArrayList<Dizimo>()

    Dizimo dizimo

    Igreja comunidadeSearch
    String dizimistaSearch
    String numeroCarteira

    Contribuicao contribuicao

    Boolean showDizimista = true
    Boolean showContribuicao = false
    Boolean showErroPermissao = false
    boolean disableLista = false

    Usuario usuarioLogado

    def logado

    Session session

    @Init
    public void init(){
        contribuicaoService = InjectUtils.getBean('contribuicaoService')
        comunidadeService = InjectUtils.getBean('comunidadeService')
        dizimoService = InjectUtils.getBean('dizimoService')
        loginService = InjectUtils.getBean('loginService')

        session = Sessions.getCurrent()
        usuarioLogado = (Usuario)session.getAttribute('usuario')
        logado = loginService.permissaoLogado(usuarioLogado, 'DIZIMO_CAP')

        if(logado == null){
            logado = loginService.permissaoLogado(usuarioLogado, 'ADMIN_DIZIMO')
            if(logado != null ){
                showErroPermissao = false
                showDizimista = true
                showContribuicao = false
            }else{
                showErroPermissao = true
                showDizimista = false
                showContribuicao = false
            }
        }else{
            if(logado.comunidade != null && logado.permissao != false){
                comunidadeSearch = Igreja.createCriteria().get {
                    eq('tipo', 'COM')
                    eq('nome',logado.comunidade.nome)
                }
                disableLista = true
                showErroPermissao = false
                showDizimista = true
                showContribuicao = false
                dizimistaList = dizimoService.getDizimistas(comunidadeSearch)
            }else{
                dizimistaList = dizimoService.getDizimistas(null)
                disableLista = false
                showErroPermissao = false
                showDizimista = true
                showContribuicao = false
            }
        }

        comunidadesList = comunidadeService.getComunidades()
    }

    @Command
    @NotifyChange(['*'])
    public void abrirContribuicao(){
        contribuicaoList = contribuicaoService.getContribuicao(dizimo.dizimista, year.format(new Date()).toInteger())
        if(contribuicaoList.size() == 0){
            contribuicaoList.clear()
            //Paroquiano paroquiano = Paroquiano.createCriteria().get {eq('id',dizimo.dizimista.id)}
            contribuicaoService.getMesContribuicao(year.format(new Date()).toInteger(), 1.toInteger(),  1.toInteger(), dizimo.dizimista)
            contribuicaoList = contribuicaoService.getContribuicao(dizimo.dizimista, year.format(new Date()).toInteger())
        }
        dizimistaSearch = dizimo.dizimista.nome
        showContribuicao = true
        showDizimista = false
    }

    @Command
    @NotifyChange(['*'])
    public void apurarMes(){
        if(contribuicao.dataApuracao != null){
            contribuicao = new Contribuicao()
            contribuicaoList = contribuicaoService.getContribuicao(dizimo.dizimista, year.format(new Date()).toInteger())
            Messagebox.show("O dizimista já colaborou esse mês,\n verifique o mês a ser apurado.", "Ecclesia System", Messagebox.OK, Messagebox.EXCLAMATION)
        }else if(contribuicao.valor < 1) {
            Messagebox.show("O valor de contribuição não pode ser 0.", "Ecclesia System", Messagebox.OK, Messagebox.EXCLAMATION)
        }else{
            contribuicao.dataApuracao = new Date()
            contribuicaoService.saveContribuicao(contribuicao)
        }
    }

    @Command
    @NotifyChange(['*'])
    public voltar(){
        contribuicao = new Contribuicao()
        dizimo = new Dizimo()
        showContribuicao = false
        showDizimista = true
        dizimistaSearch = ""
    }
}
