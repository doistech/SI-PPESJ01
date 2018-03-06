package br.com.doistech.ecclesiasystem.domain

class Paroquiano {

    //Atributos Padrões
    String criadoPor = 'Ecclesia System'
    String atualizadoPor = 'Ecclesia System'
    Date dataCriacao = new Date()
    Date dataAtualizacao = new Date()

    String nome
    Date dataNascimento
    Date dataObto
    String sexo
    String rg
    String cpf
    // Dizimistas
    Boolean dizimista
    String numeroCarteira
    // Dados de Endereço
    Endereco endereco
    Paroquiano pai
    Paroquiano mae
    //Dados de casamento
    Paroquiano conjuge
    Date dataCasamento

    Usuario usuario

    Aptidao profissao

    Igreja comunidade

    static constraints = {
        nome nullable: true
        dataNascimento nullable: true
        dataObto nullable: true
        sexo nullable: true
        rg nullable: true
        cpf nullable: true
        // Dizimistas
        dizimista nullable: true
        numeroCarteira nullable: true
        // Dados de Endereço
        endereco nullable: true
        pai nullable: true
        mae nullable: true
        //Dados de casamento
        conjuge nullable: true
        dataCasamento nullable: true
        profissao nullable: true
        usuario nullable: true
    }
}
