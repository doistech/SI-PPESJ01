package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.Constant.Constant
import br.com.doistech.ecclesiasystem.DTO.ClassCSSMenuDTO
import br.com.doistech.ecclesiasystem.domain.Usuario
import org.zkoss.bind.annotation.BindingParam
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.bind.annotation.NotifyChange
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.Session
import org.zkoss.zk.ui.Sessions

class NavegacaoVM {

    Session session

    Usuario usuarioLogado = new Usuario()
    Constant constant = new Constant()
    ClassCSSMenuDTO menuClass = new ClassCSSMenuDTO()

    String currentPage

    @Init
    public void init(){
        // currentPage = constant.DIZIMO_APP
        session = Sessions.getCurrent()
        usuarioLogado = (Usuario)session.getAttribute('usuario')
        if(usuarioLogado == null){
            Executions.sendRedirect("/login.zul")
        }
    }

    @Command
    @NotifyChange(['currentPage','menuClass'])
    public void changePage(@BindingParam("contexto") String contexto){
        String page = contexto
        menuClass.mudancaMenu(page)
        if(page.equals("paroquiano")){
            currentPage = constant.PAROQUIANO_APP
        }else if(page.equals("dizimo")){
            currentPage = constant.DIZIMO_APP
        }else if(page.equals("pedido")){
            currentPage = constant.PEDIDO_EXAME_APP
        }else if(page.equals("itemExame")){
            currentPage = constant.ITEM_EXAME_APP
        }else if(page.equals("banner")){
            currentPage = constant.BANNER_APP
        }else if(page.equals("config")){
            currentPage = constant.CONFIG_APP
        }else if(page.equals("avisos")){
            currentPage = constant.AVISOS_APP
        }
    }

    @Command
    public logoff(){
        if(session !=null){
            session.removeAttribute("usuario")
        }
        Executions.sendRedirect("/login.zul")
    }
}
