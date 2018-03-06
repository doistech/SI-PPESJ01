package br.com.doistech.ecclesiasystem.service

import br.com.doistech.ecclesiasystem.domain.GrupoPermissao
import br.com.doistech.ecclesiasystem.domain.Paroquiano
import br.com.doistech.ecclesiasystem.domain.Permissao
import br.com.doistech.ecclesiasystem.domain.Usuario
import grails.transaction.Transactional
import org.hibernate.FetchMode

@Transactional
class LoginService {

    public def permissaoLogado (Usuario usuario, String app){
        Paroquiano paroquiano = Paroquiano.createCriteria().get {
            eq('usuario', usuario)
            fetchMode('usuario', FetchMode.JOIN)
            fetchMode('comunidade', FetchMode.JOIN)
        }

        Permissao permissao = Permissao.createCriteria().get {
            eq('nome', app)
        }

        if(paroquiano != null){
            if(permissao != null) {
                GrupoPermissao grupoPermissao = GrupoPermissao.createCriteria().get {
                    eq('usuario', usuario)
                    eq('permissao', permissao)
                }
                if(grupoPermissao != null){
                    return [usuario:usuario, comunidade: paroquiano.comunidade, permissao:true]
                }else{
                    return null
                }
            }
        }else {
            GrupoPermissao grupoPermissao = GrupoPermissao.createCriteria().get {
                eq('usuario', usuario)
                fetchMode('permissao', FetchMode.JOIN)
            }
            if(grupoPermissao.permissao.nome.contains('admin')){
                return [usuario:usuario, comunidade: null, permissao:true]
            } else{
                return [usuario:usuario, comunidade: null, permissao:false]
            }
        }
    }

}
