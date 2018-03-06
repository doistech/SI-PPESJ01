package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.domain.Aviso
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange

class AvisosVM {

    Aviso aviso = new Aviso()
    String hora
    def horarios = ['04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00', '16:00', '17:00', '18:00', '19:00','20:00','21:00', '22:00', '23:00','00:00']

    Boolean showList = true
    Boolean showForm  = false

    List<Aviso> avisoList = new ArrayList<Aviso>()

    @Init
    public void init(){
        avisosAtivos()
    }

    @Command
    @NotifyChange(['aviso'])
    public void  saveAviso(){
//        aviso.hora = '07:00'
        if(aviso.titulo == null){
            println 'titulo null'
        }else if(aviso.dataEvento == null){
            println 'dataEvento null'
        }else if(aviso.hora == null ){
            println 'hora null'
        }else if(aviso.descricao == null){
            println 'descricao null'
        }else if(aviso.dataInicioVigencia == null){
            println 'Data Inicio null'
        }else if(aviso.dataFimVigencia == null){
            println 'Data Fim null'
        }else {
            aviso.save(flush:true)
            if( aviso.hasErrors()){
                println aviso.errors
            }else{
                aviso = new Aviso()
            }
        }
    }

    @Command
    @NotifyChange(['*'])
    public void avisosAtivos(){
        Date currentDate = new Date()
        avisoList = Aviso.createCriteria().list {
            le('dataInicioVigencia',currentDate)
            ge('dataFimVigencia', currentDate)
        }
    }

    @Command
    @NotifyChange(['*'])
    public void novoAviso(){
        aviso = new Aviso()
        showList = false
        showForm  = true
    }

    @Command
    @NotifyChange(['*'])
    public void voltarList(){
        showList = true
        showForm  = false
        avisosAtivos()
    }
}

