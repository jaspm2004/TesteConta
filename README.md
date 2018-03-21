# TesteConta

API de para gestão/manutenção de contas de saldo. Oferece uma interface REST para:

* Gestão de Pessoas (Física e Jurídica)
* Gestão de Contas (Matriz e Filial)
* Gestão de Transações (Aportes e Transferências)

### Tecnologias utilizadas

* Maven
* Spring Boot
* H2

### Para executar este projeto localmente
```
$ git clone https://github.com/jaspm2004/TesteConta
$ cd TesteConta
$ mvn clean install
$ mvn dependency:copy-dependencies
$ cd target
$ java -jar .\TesteConta-1.0.0.jar
```
Durante a execução são criadas várias entradas no banco de dados para facilitar os testes, para mais detalhes consultar o arquivo TesteConta\src\main\resources\import.sql.

Após executar, a API estará disponível em http://localhost:8080/testecontaapi/ . Se for preciso mudar a porta, modifique o arquivo TesteConta\src\main\resources\application.yml segundo corresponda.

### Para importar este projeto utilizando IDE

* Fazer o clone: https://github.com/jaspm2004/TesteConta
* Clean & Build
* Run

### Exemplos de uso
* Para criar uma Pessoa Física
```
POST em http://localhost:8080/testecontaapi/pf 

- Passar os parâmetros abaixo, todos são obrigatórios, no Request Body: 
* "cpf": "XXX.XXX.XXX-XX",
* "nome": "String", 
* "dataNascimento": "dd-mm-aaaa"
```
* Para listar Pessoa Física
```
GET em http://localhost:8080/testecontaapi/pf (lista todas as pessoas físicas cadastradas)
GET em http://localhost:8080/testecontaapi/pf?cpf=<CPF da pessoa> (filtra pelo cpf, que no caso é também o id)
```
* Para criar uma Pessoa Jurídica
```
POST em http://localhost:8080/testecontaapi/pj 

- Passar os parâmetros abaixo, todos são obrigatórios, no Request Body: 
* "cnpj": "XX.XXX.XXX/XXXX-XX",
* "nomeFantasia": "String", 
* "razaoSocial": "String"
```
* Para listar Pessoa Jurídica
```
GET em http://localhost:8080/testecontaapi/pj (lista todas as pessoas jurídicas cadastradas)
GET em http://localhost:8080/testecontaapi/pj?cnpj=<CNPJ da pessoa> (filtra pelo cnpj, que no caso é também o id)
```
* Para criar uma Conta
```
POST em http://localhost:8080/testecontaapi/conta

- Passar os parâmetros abaixo, no Request Body: 
* "nome": "String",       obrigatório
* "saldo": Long,          default = 0
* "status": "String",     obrigatório, ["ATIVA", "BLOQUEADA", "CANCELADA"]
* "pessoa": {"id": Long}, obrigatório, referência para a pessoa atrelada à conta
* "mae": {"id": Long}     referência para conta "mãe", deixar vazia no caso de Conta Matriz
```
* Para listar Conta
```
GET em http://localhost:8080/testecontaapi/conta (lista todas as contas cadastradas)
GET em http://localhost:8080/testecontaapi/conta/<id> (filtra pelo id da conta)
```
* Para criar um Aporte

Os aportes podem ser recebidos apenas por uma Conta Matriz, com status = "ATIVA". Cada aporte realizado com sucesso recebe um identificador alfanumérico único e o status da transação é atualizado automaticamente para "PROCESSADA"
```
POST em http://localhost:8080/testecontaapi/aporte

- Passar os parâmetros abaixo, todos são obrigatórios, no Request Body: 
* "valor": Long,
* "conta": {"id": Long}    referência para conta Matriz que vai receber o aporte
```
* Para listar Aporte
```
GET em http://localhost:8080/testecontaapi/aporte (lista todos os aportes cadastrados)
GET em http://localhost:8080/testecontaapi/aporte?id=<código alfanumérico> (filtra pelo id do aporte)
GET em http://localhost:8080/testecontaapi/aporte?conta=<id da conta> (lista os aportes atrelados à conta especificada)
```
* Para estornar Aporte

Se o estorno for realizado com sucesso, o status da transação é atualizado automaticamente para "ESTORNADA". Não é possível estornar um Aporte cujo status de transação é "ESTORNADA".
```
PATCH em http://localhost:8080/testecontaapi/aporte?id=<código alfanumérico>
```
* Para criar uma Transferência

As transferências podem ter origem em uma conta Matriz ou Filial, com status = "ATIVA", e destino apenas em uma conta Filial, com status = "ATIVA", que esteja na mesma árvore da conta origem. Cada transferência realizada com sucesso recebe um identificador único e o status da transação é atualizado automaticamente para "PROCESSADA"
```
POST em http://localhost:8080/testecontaapi/transferencia

- Passar os parâmetros abaixo, todos são obrigatórios, no Request Body: 
* "valor": Long,
* "contaOrigem": {"id": Long},    referência para a conta origem
* "contaDestino": {"id": Long}    referência para a conta destino
```
* Para listar Transferência
```
GET em http://localhost:8080/testecontaapi/transferencia (lista todas as transferência cadastradas)
GET em http://localhost:8080/testecontaapi/transferencia/<id da transferência> (filtra pelo id da transferência)
GET em http://localhost:8080/testecontaapi/transferencia?any=<id da conta> (lista as transferências que tiveram origem ou destino na conta especificada)
GET em http://localhost:8080/testecontaapi/transferencia?origem=<id da conta> (lista as transferências que tiveram origem na conta especificada)
GET em http://localhost:8080/testecontaapi/transferencia?destino=<id da conta> (lista as transferências que tiveram destino na conta especificada)
```
* Para estornar Transferência

Se o estorno for realizado com sucesso, o status da transação é atualizado automaticamente para "ESTORNADA". Não é possível estornar uma transferência cujo status de transação é "ESTORNADA".
```
PATCH em http://localhost:8080/testecontaapi/transferencia/<id da transferência>
```

