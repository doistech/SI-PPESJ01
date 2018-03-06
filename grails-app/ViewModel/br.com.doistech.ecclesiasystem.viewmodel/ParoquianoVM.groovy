package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.domain.Contato
import br.com.doistech.ecclesiasystem.domain.ContatoParoquiano
import br.com.doistech.ecclesiasystem.domain.Dizimo
import br.com.doistech.ecclesiasystem.domain.Endereco
import br.com.doistech.ecclesiasystem.domain.Igreja
import br.com.doistech.ecclesiasystem.domain.Paroquiano
import org.hibernate.FetchMode
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.zhtml.Messagebox

class ParoquianoVM {

    //String
    String sexo
    String dizimista
    String tipoContato
    String paroquianoSearch

    Igreja comunidadeSearch

    //Dominio
    ContatoParoquiano contatoParoquiano = new ContatoParoquiano()
    Paroquiano paroquiano               = new Paroquiano()
    Igreja comunidade                   = new Igreja()
    Endereco endereco                   = new Endereco()
    Contato contato                     = new Contato()
    Dizimo paroquianoDizimista       = new Dizimo()

    //Logico
    Boolean showContatoLista
    Boolean showPesquisa  = true
    Boolean showForm  = false

    //Lista
    List<Igreja> comunidadesList = new ArrayList<Igreja>()
    List<Contato>   contatosList = new ArrayList<Contato>()
    List<Paroquiano> paroquianoList = new ArrayList<Paroquiano>()

    def sexoList = ['Masculino', 'Feminino']
    def contatoList = ['Celular', 'Telefone', 'E-mail']
    def dizimoList = ['Sim', 'Não']

    @Init
    public void init(){
        getComunidades()
        getParoquianos()
    }

    @Command
    @NotifyChange(['*'])
    public void getComunidades(){
        Igreja paroquia = Igreja.createCriteria().get {eq('id', 1.toLong())}
        comunidadesList = Igreja.createCriteria().list {
            eq('paroquia',paroquia)
            eq('tipo', 'COM')
        }
    }

    @Command
    @NotifyChange(['*'])
    public void getParoquianos(){
        paroquianoList.clear()
        paroquianoList = Paroquiano.createCriteria().list {
            'in'('comunidade',comunidadesList)
            fetchMode('comunidade', FetchMode.JOIN)
        }
    }

    @Command
    @NotifyChange(['*'])
    public void editarParoquiano(){
        showPesquisa  = false
        showForm  = true
        comunidade =  paroquiano.comunidade
    }

    @Command
    @NotifyChange(['*'])
    public void retornar(){
        getParoquianos()
        showPesquisa  = true
        showForm  = false

    }
    @Command
    @NotifyChange(['*'])
    public void novoParoquiano(){
        showPesquisa  = false
        showForm  = true
        paroquiano = new Paroquiano()
    }

    @Command
    @NotifyChange(['*'])
    public void adicionarContato(){
        println 'Adicionar Contato'
        if(contato.descricao != null || tipoContato != null){
            contato.tipo = tipoContato
            contatosList.add(contato)
            contato = new Contato()
            if(contatosList.size() > 0){
                showContatoLista = true
            }
        }else{
            println 'Adicionar Contato'
            Messagebox.show("Por Favor, insira a descrição do contato.", "Ecclesia System", Messagebox.OK, Messagebox.EXCLAMATION)
        }
    }

    @Command
    @NotifyChange(['*'])
    public void pesquisaParoquiano(){
        paroquianoList = Paroquiano.createCriteria().list {
            if(comunidadeSearch != null){
                eq('comunidade', comunidadeSearch)
            }
            if(paroquianoSearch != null){
                like('nome', '%'+paroquianoSearch+'%')
            }
            fetchMode('comunidade', FetchMode.JOIN)
        }
    }

    @Command
    @NotifyChange(['*'])
    public void save(){
        if(paroquiano != null) {
            endereco.save(flush: true)
            paroquiano.endereco = endereco
            paroquiano.comunidade = comunidade

            if (paroquiano.rg == null) {
                paroquiano.rg = 'Não Informado'
            }

            if (paroquiano.cpf == null) {
                paroquiano.cpf = 'Não Informado'
            }

            if (paroquiano.cpf == null) {
                paroquiano.cpf = 'Não Informado'
            }

            if (paroquiano.dataCasamento == null) {
                paroquiano.dataCasamento = new Date()
            }

            if (paroquiano.dataObto == null) {
                paroquiano.dataObto = new Date()
            }

            if (dizimista.equals('Sim')) {
                paroquiano.dizimista = true
            } else {
                paroquiano.dizimista = false
            }
            Paroquiano.withNewSession {
                paroquiano.save(flush: true)
            }

            contatosList.each { Contato contato ->
                Contato.withNewSession {
                    contato.save(flush: true)
                }
                ContatoParoquiano.withNewSession {
                    contatoParoquiano = new ContatoParoquiano()
                    contatoParoquiano.paroquiano = paroquiano
                    contatoParoquiano.contato = contato
                    contatoParoquiano.save(flush: true)
                }
            }

            if (paroquiano.dizimista) {
                Dizimo hasDizimista = Dizimo.createCriteria().get {
                    eq('dizimista', paroquiano)
                }
                if (hasDizimista == null) {
                    comunidade = paroquiano.comunidade
                    if (comunidade.nome.equals('Matriz')) {
                        Dizimo carteirinha = Dizimo.createCriteria().get {
                            eq('idComunidade', 'MA')
                            order('id', 'desc')
                            maxResults(1)
                        }
                        if (carteirinha == null) {
                            paroquianoDizimista.count = 1
                            paroquianoDizimista.idComunidade = 'MA'
                            paroquianoDizimista.codigoCarteira = 'MA' + paroquianoDizimista.count
                        } else {
                            paroquianoDizimista.count = carteirinha.count + 1
                            paroquianoDizimista.codigoCarteira = 'MA' + paroquianoDizimista.count
                        }
                        paroquianoDizimista.dizimista = paroquiano
                        paroquianoDizimista.comunidade = comunidade
                    } else if (comunidade.nome.equals('Nossa Senhora Aparecida')) {
                        Dizimo carteirinha = Dizimo.createCriteria().get {
                            eq('idComunidade', 'AP')
                            order('id', 'desc')
                            maxResults(1)
                        }
                        if (carteirinha == null) {
                            paroquianoDizimista.count = 1
                            paroquianoDizimista.idComunidade = 'AP'
                            paroquianoDizimista.codigoCarteira = 'AP' + paroquianoDizimista.count
                        } else {
                            paroquianoDizimista.count = carteirinha.count + 1
                            paroquianoDizimista.codigoCarteira = 'AP' + paroquianoDizimista.count
                        }
                        paroquianoDizimista.dizimista = paroquiano
                        paroquianoDizimista.comunidade = comunidade
                    } else if (comunidade.nome.equals('Nossa Senhora do Sagrado Coração')) {
                        Dizimo carteirinha = Dizimo.createCriteria().get {
                            eq('idComunidade', 'NSSGC')
                            order('id', 'desc')
                            maxResults(1)
                        }
                        if (carteirinha == null) {
                            paroquianoDizimista.count = 1
                            paroquianoDizimista.idComunidade = 'NSSGC'
                            paroquianoDizimista.codigoCarteira = 'NSSGC' + paroquianoDizimista.count
                        } else {
                            paroquianoDizimista.count = carteirinha.count + 1
                            paroquianoDizimista.codigoCarteira = 'NSSGC' + paroquianoDizimista.count
                        }
                        paroquianoDizimista.dizimista = paroquiano
                        paroquianoDizimista.comunidade = comunidade
                    } else if (comunidade.nome.equals('Santa Rita')) {
                        Dizimo carteirinha = Dizimo.createCriteria().get {
                            eq('idComunidade', 'SR')
                            order('id', 'desc')
                            maxResults(1)
                        }
                        if (carteirinha == null) {
                            paroquianoDizimista.count = 1
                            paroquianoDizimista.idComunidade = 'SR'
                            paroquianoDizimista.codigoCarteira = 'SR' + paroquianoDizimista.count
                        } else {
                            paroquianoDizimista.count = carteirinha.count + 1
                            paroquianoDizimista.codigoCarteira = 'SR' + paroquianoDizimista.count
                        }
                        paroquianoDizimista.dizimista = paroquiano
                        paroquianoDizimista.comunidade = comunidade
                    } else if (comunidade.nome.equals('São Jorge')) {
                        Dizimo carteirinha = Dizimo.createCriteria().get {
                            eq('idComunidade', 'SJ')
                            order('id', 'desc')
                            maxResults(1)
                        }
                        if (carteirinha == null) {
                            paroquianoDizimista.count = 1
                            paroquianoDizimista.idComunidade = 'SJ'
                            paroquianoDizimista.codigoCarteira = 'SJ' + paroquianoDizimista.count
                        } else {
                            paroquianoDizimista.count = carteirinha.count + 1
                            paroquianoDizimista.codigoCarteira = 'SJ' + paroquianoDizimista.count
                        }
                        paroquianoDizimista.dizimista = paroquiano
                        paroquianoDizimista.comunidade = comunidade
                    }
                    paroquianoDizimista.save(flush: true)
                    Messagebox.show("Paroquiano inserido com sucesso!.", "Ecclesia System", Messagebox.OK, Messagebox.EXCLAMATION)
                    clearForm()
                }
            } else {
                Messagebox.show("Existe algum campo vazio.", "Ecclesia System", Messagebox.OK, Messagebox.EXCLAMATION)
            }
        }

    }

    @NotifyChange(['*'])
    public void clearForm(){
        contatoParoquiano        = new ContatoParoquiano()
        paroquiano               = new Paroquiano()
        comunidade               = new Igreja()
        endereco                 = new Endereco()
        contato                  = new Contato()
        paroquianoDizimista      = new Dizimo()
        contatosList.clear()
    }
//    @Command
//    @NotifyChange(['*'])
//    public void save(){
//
//    }
}
