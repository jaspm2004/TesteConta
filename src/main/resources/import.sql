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
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid) VALUES (1, 'Conta Matriz 1', 0, 'ATIVA', CURRENT_TIMESTAMP(), 1);

-- conta filial 1
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid, maeid) VALUES (2, 'Conta Filial 1', 1000, 'ATIVA', CURRENT_TIMESTAMP(), 2, 1);

-- conta filial 2
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid, maeid) VALUES (3, 'Conta Filial 2', 0, 'CANCELADA', CURRENT_TIMESTAMP(), 3, 2);

-- conta filial 3
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid, maeid) VALUES (4, 'Conta Filial 3', 0, 'BLOQUEADA', CURRENT_TIMESTAMP(), 4, 1);

-- conta filial 4
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid, maeid) VALUES (5, 'Conta Filial 4', 0, 'ATIVA', CURRENT_TIMESTAMP(), 5, 3);

-- conta matriz 2
INSERT INTO conta (id, nome, saldo, status_conta, data_criacao, pessoaid) VALUES (6, 'Conta Matriz 2', 0, 'CANCELADA', CURRENT_TIMESTAMP(), 1);
