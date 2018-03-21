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
$ mvn clean install
$ mvn dependency:copy-dependencies
$ cd target
$ java -jar .\TesteConta-1.0.0.jar
```
A API REST está disponível em http://localhost:8080/testecontaapi/ . Se for preciso mudar a porta, modifique o arquivo application.yml segundo corresponda.

### Para importar este projeto utilizando IDE

* Fazer o clone: https://github.com/jaspm2004/TesteConta
* Clean & Build
* Run

### Exemplos de uso
* Para criar uma Pessoa Física
```
POST em http://localhost:8080/testecontaapi/pf 

- Passar os parâmetros abaixo, todos são obrigatórios, no Request Body: 
* cpf = <String>, formato: [XXX.XXX.XXX-XX]
* nome = <String>, 
* dataNascimento = <String> formato: [dd-mm-aaaa]
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
* cnpj = <String>, formato: [XX.XXX.XXX/XXXX-XX]
* nomeFantasia = <String>, 
* razaoSocial = <String>
```
* Para listar Pessoa Jurídica
```
GET em http://localhost:8080/testecontaapi/pj (lista todas as pessoas jurídicas cadastradas)
GET em http://localhost:8080/testecontaapi/pj?cnpj=<CNPJ da pessoa> (filtra pelo cnpj, que no caso é também o id)
```

