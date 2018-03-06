package br.com.doistech.ecclesiasystem.viewmodel

import br.com.doistech.ecclesiasystem.domain.Usuario
import org.zkoss.bind.annotation.Command
import org.zkoss.bind.annotation.Init
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.Session
import org.zkoss.zk.ui.Sessions

class LoginVM {

    Usuario usuarioLogin = new Usuario()
    Usuario usuario = new Usuario()
    Session session

    @Init
    public init(){
        session = Sessions.getCurrent()
    }

    @Command
    public authentication(){
        if(usuarioLogin != null){
            usuario = Usuario.createCriteria().get {
                eq('usuario', usuarioLogin.usuario)
                eq('senha', usuarioLogin.senha)
            }
            if(usuario != null){
                session.setAttribute("usuario", usuario)
                Executions.sendRedirect("/index.zul")
            } else {
                //Mensagem de senha errada
            }
        }else{
            //Mensagem dados null
        }
    }

    @Command
    public logoff(){
        session.removeAttribute("usuario")
        Executions.sendRedirect("/login.zul")
    }
}
