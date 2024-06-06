# Sistema de Gerenciamento de Estacionamento - Alaloka

# Objetivo

O projeto "Alaloka Estacionamento" tem como objetivo desenvolver um sistema completo para gerenciar estacionamentos, incluindo o cadastro e controle de vagas, clientes, carros, tickets e cancelas. A solução visa automatizar processos, otimizar o uso do espaço e facilitar a gestão financeira do estacionamento.

## Rol de Requisitos:

## Requisitos Funcionais:

- ### Cadastro de Vagas:

1.1 Permitir o cadastro de novas vagas com informações como número, tipo (carro, moto, especial) e disponibilidade.

1.2 Listar todas as vagas cadastradas.

1.3 Buscar uma vaga específica pelo ID.

1.4 Atualizar os dados de uma vaga existente.

1.5 Excluir uma vaga.

- ### Cadastro de Carros:

2.1 Permitir o cadastro de carros com informações como placa e tipo (carro, moto).

2.2 Listar todos os carros cadastrados.

2.3 Buscar um carro específico pelo ID.

2.4 Atualizar os dados de um carro existente.

2.5 Excluir um carro.

- ### Cadastro de Clientes:

3.1 Permitir o cadastro de clientes com informações como nome, CPF e telefone.

3.2 Listar todos os clientes cadastrados.

3.3 Buscar um cliente específico pelo ID.

3.4 Atualizar os dados de um cliente existente.

3.5 Excluir um cliente.

- ### Gerenciamento de Tickets:

4.1 Permitir a criação de um novo ticket associado a uma vaga, carro e cliente, registrando a data e hora de entrada.

4.2 Listar todos os tickets ativos.

4.3 Buscar um ticket específico pelo ID.

4.4 Atualizar um ticket existente, registrando a data e hora de saída e o valor pago.

4.5 Excluir um ticket.

- ### Controle de Cancela:

5.1 Permitir a abertura e fechamento da cancela.

5.2 Consultar o status atual da cancela (aberta ou fechada).


## Requisitos Não Funcionais:

- ### Segurança: Proteger os dados do sistema contra acessos não autorizados.

- ### Performance: Garantir um bom desempenho do sistema, mesmo com um grande volume de dados.

- ### Usabilidade: Ter uma interface intuitiva e fácil de usar.

- ### Manutenibilidade: Facilitar a manutenção e evolução do sistema.

## Regras de Negócio:

- ### Vagas:

1.1 Uma vaga só pode ser ocupada por um carro por vez.

1.2 O sistema deve garantir que o número da vaga seja único.

- ### Tickets:

2.1 Um ticket só pode ser criado se houver uma vaga disponível do tipo correto (carro ou moto).

2.2 A data de saída de um ticket deve ser posterior à data de entrada.

2.3 O valor pago deve ser calculado com base no tempo de permanência e na tarifa do estacionamento.

- ### Cancela:

3.1 A cancela só pode ser aberta se houver um ticket ativo associado à vaga.

3.2 A cancela deve ser fechada automaticamente após a saída do carro.
