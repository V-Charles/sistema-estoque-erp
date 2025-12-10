# Sistema de Gestão de Estoque (ERP) - Backend Core

## Sobre o Projeto
Este projeto consiste na Etapa 6 do Projeto Integrador do Curso Técnico em Desenvolvimento de Sistemas. O objetivo principal foi a modernização e refatoração de um sistema de gestão de estoque legado (originalmente Desktop/Swing) para uma arquitetura moderna e desacoplada, preparando o terreno para uma futura interface Web.

Nesta fase, o foco foi o desenvolvimento do Backend (Núcleo da Aplicação), isolando as Regras de Negócio da Interface de Usuário, aplicando princípios SOLID e Clean Architecture. O sistema permite o controle granular de produtos, fornecedores, usuários e o fluxo completo de movimentações de estoque (entradas e saídas).

## Funcionalidades Principais
O sistema, atualmente executado via Console para validação de lógica, oferece as seguintes capacidades:

* **Gestão de Produtos:** Cadastro com validação de preços (custo/venda) e vínculo obrigatório com Categoria e Fornecedor.
* **Controle de Estoque:**
    * Registro de Entradas (Compras), Saídas (Vendas) e Ajustes.
    * Validação automática de saldo insuficiente para saídas.
    * Histórico financeiro (gravação do valor unitário no momento da transação).
* **Gestão de Fornecedores:** Cadastro com validação e limpeza automática de máscaras (CNPJ/Telefone).
* **Autenticação e Segurança:**
    * Sistema de Login com verificação de status (Ativo/Inativo).
    * Criptografia de senhas utilizando hash MD5.

## Diagrama do Sistema
Abaixo, o Diagrama de Classes UML que representa a Arquitetura em Camadas (Layered Architecture) implementada. Ele evidencia a separação entre Entidades (`Model`) e Regras de Negócio (`Service`) através do uso de setas de Dependência (linhas pontilhadas).

![Diagrama de Classes do Sistema](https://github.com/user-attachments/assets/a2a88d5c-894b-46ef-a80e-e5039bdc0119)

## Tecnologias Utilizadas
* **Linguagem:** Java (JDK 21+)
* **Gerenciamento de Dependências:** Maven
* **IDE:** IntelliJ IDEA / NetBeans
* **Controle de Versão:** Git & GitHub
* **API de Datas:** `java.time` (LocalDate/LocalDateTime) para precisão temporal.
* **Segurança:** Implementação de criptografia MD5 para senhas.

## Regras de Negócio Importantes
O sistema implementa as seguintes regras de proteção de dados e lógica na camada de Service:

* **Integridade de Estoque:** O `EstoqueService` impede a realização de saídas caso a quantidade solicitada seja maior que o saldo atual do produto.
* **Histórico de Preços:** Ao registrar uma movimentação, o sistema salva o valor do produto naquele instante (`valorUnitario` na `Movimentacao`), garantindo que alterações futuras no preço do produto não afetem o histórico financeiro.
* **Sanitização de Dados:** O `FornecedorService` remove automaticamente caracteres especiais de CNPJ e Telefone.
* **Validação Financeira:** O `ProdutoService` impede o cadastro de produtos com valores de custo ou venda negativos.
* **Segurança de Acesso:** O `UsuarioService` rejeita tentativas de login de usuários marcados como "Inativos".

## Requisitos Não Funcionais
* **Arquitetura em Camadas:** O código é dividido estritamente em `Model`, `Service` e `App`.
* **Responsabilidade Única (SRP):** As classes possuem responsabilidades bem definidas (ex: Service só valida e orquestra; Model só armazena dados).
* **Segurança:** Senhas são criptografadas (MD5).
* **Compatibilidade:** O núcleo do sistema é independente de framework visual (Swing ou Web).

## Como Executar o Projeto

1.  **Pré-requisitos:** Certifique-se de ter o Java JDK 17 (ou superior) e o Maven instalados.
2.  **Clonar o Repositório:**
    ```bash
    git clone [https://github.com/V-Charles/sistema-estoque-erp](https://github.com/V-Charles/sistema-estoque-erp)
    ```
3.  **Importar:** Abra o projeto no sua IDE de preferência (IntelliJ ou NetBeans) selecionando o arquivo `pom.xml`.
4.  **Executar:**
    * Navegue até o pacote `br.com.vinicius.estoque.app`.
    * Execute a classe `Main.java`.
    * O console exibirá a simulação dos testes de cadastro, movimentação e validação de erros.

## Licença
Este projeto é desenvolvido para fins educacionais como parte do curso Técnico em Desenvolvimento de Sistemas do Senac.

---
*Desenvolvido por Vinicius Charles - 2025*
