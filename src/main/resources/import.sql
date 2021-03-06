-- pessoa fisica 1
INSERT INTO pessoa_fisica (nome, cpf, data_nascimento) VALUES ('Jose PF', '123.345', '1981-11-18');
-- insere na tabela de ligação
INSERT INTO pessoa (id, cpf) VALUES (1, '123.345');

-- pessoa fisica 2
INSERT INTO pessoa_fisica (nome, cpf, data_nascimento) VALUES ('Pedro PF', '123.678', '1980-01-20');
-- insere na tabela de ligação
INSERT INTO pessoa (id, cpf) VALUES (2, '123.678');

-- pessoa jurídica 1
INSERT INTO pessoa_juridica (nome_fantasia, cnpj, razao_social) VALUES ('Empresa I PJ', '0987.0001', 'Dist. Carros e Motos');
-- insere na tabela de ligação
INSERT INTO pessoa (id, cnpj) VALUES (3, '0987.0001');

-- pessoa jurídica 2
INSERT INTO pessoa_juridica (nome_fantasia, cnpj, razao_social) VALUES ('Empresa II PJ', '0456.0001', 'Fornec. Bens e Produtos');
-- insere na tabela de ligação
INSERT INTO pessoa (id, cnpj) VALUES (4, '0456.0001');

-- pessoa jurídica 3
INSERT INTO pessoa_juridica (nome_fantasia, cnpj, razao_social) VALUES ('Empresa III PJ', '0123.0001', 'Fornec. Alimentos');
-- insere na tabela de ligação
INSERT INTO pessoa (id, cnpj) VALUES (5, '0123.0001');

-- conta matriz 1
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid) VALUES (1, 'Conta Matriz 1', 1000, 'ATIVA', CURRENT_TIMESTAMP(), 1);

-- conta filial 1
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid, maeid) VALUES (2, 'Conta Filial 1', 1000, 'ATIVA', CURRENT_TIMESTAMP(), 2, 1);

-- conta filial 2
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid, maeid) VALUES (3, 'Conta Filial 2', 300, 'CANCELADA', CURRENT_TIMESTAMP(), 3, 2);

-- conta filial 3
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid, maeid) VALUES (4, 'Conta Filial 3', 300, 'ATIVA', CURRENT_TIMESTAMP(), 4, 1);

-- conta filial 4
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid, maeid) VALUES (5, 'Conta Filial 4', 300, 'ATIVA', CURRENT_TIMESTAMP(), 5, 3);

-- conta matriz 2
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid) VALUES (6, 'Conta Matriz 2', 200, 'CANCELADA', CURRENT_TIMESTAMP(), 1);

-- conta matriz 5
INSERT INTO conta (id, nome, saldo, status, data_criacao, pessoaid, maeid) VALUES (7, 'Conta Filial 5', 200, 'ATIVA', CURRENT_TIMESTAMP(), 2, 2);

-- aporte na conta matriz 1
INSERT INTO aporte (id, valor, contaid, status) VALUES ('123AP1', 100, 1, 'PROCESSADA');

-- aporte na conta matriz 2
INSERT INTO aporte (id, valor, contaid, status) VALUES ('456AP2', 100, 6, 'PROCESSADA');

-- transferência 1
INSERT INTO transferencia (id, valor, conta1id, conta2id, status) VALUES (1, 100, 1, 4, 'PROCESSADA');

-- transferência 2
INSERT INTO transferencia (id, valor, conta1id, conta2id, status) VALUES (2, 200, 1, 3, 'PROCESSADA');

-- transferência 3
INSERT INTO transferencia (id, valor, conta1id, conta2id, status) VALUES (3, 300, 4, 5, 'PROCESSADA');
