insert into manutencao (id, nome) values (1, 'Computadores');
insert into manutencao (id, nome) values (2, 'NoteBook');

insert into loja (id, nome, taxa_frete, manutencao_id) values (1, 'pc mager', 10, 1);
insert into loja (id, nome, taxa_frete, manutencao_id) values (2, 'manutencao Delivery', 9.50, 1);
insert into loja (id, nome, taxa_frete, manutencao_id) values (3, 'manu notebook', 15, 2);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_MANUTENCOES', 'Permite consultar manutencoes');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_MANUTENCOES', 'Permite editar manutencoes');
