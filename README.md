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
A API REST está disponível em http://localhost:8080/testecontaapi/

### Para importar este projeto utilizando IDE

* Fazer o clone: https://github.com/jaspm2004/TesteConta
* Clean & Build
* Run

### Exemplos de uso
* Para criar uma Pessoa Física
```
fazer um POST em http://localhost:8080/testecontaapi/pf utilizando os parâmetros, todos são obrigatórios, no Request Body: 
* cpf = <String>, formato: [XXX.XXX.XXX-XX]
* nome = <String>, 
* dataNascimento = <String> formato: [dd-mm-aaaa]
```
* Para listar Pessoa Física
```
fazer um GET em http://localhost:8080/testecontaapi/pf (lista todas as pessoas físicas cadastradas)
fazer um GET em http://localhost:8080/testecontaapi/pf?cpf=<CPF da pessoa> (filtra pelo cpf, que no caso é também o id)
```
