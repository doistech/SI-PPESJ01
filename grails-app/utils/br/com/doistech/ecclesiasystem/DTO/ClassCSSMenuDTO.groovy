package br.com.doistech.ecclesiasystem.DTO

class ClassCSSMenuDTO {
    String activePacienteClass
    String activeExameClass
    String activeItemExameClass
    String activePedidoExameClass
    String activeConfigiuracaoClass
    String activeResultadoClass
    String activeConfigClass

    public void mudancaMenu(String menu){
        limpezaMenu()
        if(menu.equals('paciente')){
            activePacienteClass = 'active'
        }else if(menu.equals('exame')){
            activeExameClass = 'active'
        }else if(menu.equals('itemExame')){
            activeItemExameClass = 'active'
        }else if(menu.equals('pedido')){
            activePedidoExameClass = 'active'
        }else if(menu.equals('resultado')){
            activeResultadoClass = 'active'
        }else if(menu.equals('config')){
            activeConfigClass = 'active'
        }
    }

    public void limpezaMenu(){
        activePacienteClass = ""
        activeExameClass = ""
        activeItemExameClass = ""
        activePedidoExameClass = ""
        activeConfigiuracaoClass = ""
        activeResultadoClass = ""
        activeConfigClass = ""
    }
}
