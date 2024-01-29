# language: pt

Funcionalidade: Produção

  Cenario: Cadastrar uma nova produção
    Quando eu cadastrar uma nova produção
    Então a produção deve ser cadastrada com sucesso

  Cenario: Listar as produções existentes
    Dado que uma produção já foi cadastrada
    Quando requisitar a lista de produções
    Então as produções devem ser exibidas com sucesso

  Cenario: Cadastrar produção de pedido já existente
    Dado que uma produção já foi cadastrada
    Quando eu cadastrar uma nova produção com o mesmo pedido
    Então a produção não deve ser cadastrada com sucesso

  Cenario: Atualizar o status de uma produção
    Dado que uma produção já foi cadastrada
    Quando requisitar a atualização do status da produção
    Então a produção deve ser atualizada com sucesso