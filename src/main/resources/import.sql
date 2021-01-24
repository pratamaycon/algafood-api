
-- tabela cozinha --
insert into cozinha (nome) values ('Tailandesa')
insert into cozinha (nome) values ('Indiana')
insert into cozinha (nome) values ('Brasileira')

-- tabela restaurante --
insert into restaurante (nome, taxa_frete, cod_cozinha) values ('Good Burguer', 5.0, 3)
insert into restaurante (nome, taxa_frete, cod_cozinha) values ('Peça Pizzas', 3.0, 3)
insert into restaurante (nome, taxa_frete, cod_cozinha) values ('Thai Gourmet', 3.0, 1)
insert into restaurante (nome, taxa_frete, cod_cozinha) values ('Thai Delivery', 3.0, 1)
insert into restaurante (nome, taxa_frete, cod_cozinha) values ('Tuk tuk Comida Indiana', 3.0, 1)

-- tabela estado --
INSERT INTO estado (nome) VALUES('Acre');
INSERT INTO estado (nome) VALUES('Alagoas');
INSERT INTO estado (nome) VALUES('Amazonas');
INSERT INTO estado (nome) VALUES('Amapá');
INSERT INTO estado (nome) VALUES('Bahia');
INSERT INTO estado (nome) VALUES('Ceará');
INSERT INTO estado (nome) VALUES('Distrito Federal');
INSERT INTO estado (nome) VALUES('Espírito Santo');
INSERT INTO estado (nome) VALUES('Goiás');
INSERT INTO estado (nome) VALUES('Maranhão');
INSERT INTO estado (nome) VALUES('Minas Gerais');
INSERT INTO estado (nome) VALUES('Mato Grosso do Sul');
INSERT INTO estado (nome) VALUES('Mato Grosso');
INSERT INTO estado (nome) VALUES('Pará');
INSERT INTO estado (nome) VALUES('Paraíba');
INSERT INTO estado (nome) VALUES('Pernambuco');
INSERT INTO estado (nome) VALUES('Piauí');
INSERT INTO estado (nome) VALUES('Paraná');
INSERT INTO estado (nome) VALUES('Rio de Janeiro');
INSERT INTO estado (nome) VALUES('Rio Grande do Norte');
INSERT INTO estado (nome) VALUES('Rondônia');
INSERT INTO estado (nome) VALUES('Roraima');
INSERT INTO estado (nome) VALUES('Rio Grande do Sul');
INSERT INTO estado (nome) VALUES('Santa Catarina');
INSERT INTO estado (nome) VALUES('Sergipe');
INSERT INTO estado (nome) VALUES('São Paulo');
INSERT INTO estado (nome) VALUES('Tocantins');

-- tabela estado --
INSERT INTO cidade (nome, cod_estado) VALUES ('Belo Horizonte', 11);
INSERT INTO cidade (nome, cod_estado) VALUES ('Uberlândia', 11);
INSERT INTO cidade (nome, cod_estado) VALUES ('Uberaba', 11);
INSERT INTO cidade (nome, cod_estado) VALUES ('São Paulo', 26);
INSERT INTO cidade (nome, cod_estado) VALUES ('Campinas', 26);
INSERT INTO cidade (nome, cod_estado) VALUES ('Rio de Janeiro', 19);
INSERT INTO cidade (nome, cod_estado) VALUES ('Angra dos Reis', 19);
INSERT INTO cidade (nome, cod_estado) VALUES ('Goiânia', 9);
INSERT INTO cidade (nome, cod_estado) VALUES ('Caldas Novas', 9);

-- tabela forma_pagamento --
INSERT INTO forma_pagamento (descricao) VALUES ('Credito');
INSERT INTO forma_pagamento (descricao) VALUES ('Debito');
INSERT INTO forma_pagamento (descricao) values ('Dinheiro');

-- tabela permissao -- 
insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

