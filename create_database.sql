create database financas;

use financas;

create table receitas(
id int identity(1,1) primary key,
valor bigint,
data_recebimento date,
data_recebimento_esperado date,
descricao varchar(50),
conta varchar(30),
tipo_receita varchar (50)
);

create table despesas(
id int identity(1,1) primary key,
valor bigint,
data_pagamento date,
data_pagamento_esperado date,
tipo_despesa varchar(50),
conta varchar(30)
);


create table contas(
id int identity(1,1) primary key,
saldo bigint,
tipo_conta varchar(50),
instituicao_financeira varchar(50)
);

use financas;

insert into contas (saldo, tipo_conta, instituicao_financeira) values (5000, 'POUPANCA', 'caixa economica');
insert into contas (saldo, tipo_conta, instituicao_financeira) values (1000, 'CARTEIRA', 'ailos');
insert into contas (saldo, tipo_conta, instituicao_financeira) values (2500, 'CONTA_CORRENTE', 'itau');

insert into despesas (valor, data_pagamento, data_pagamento_esperado, tipo_despesa, conta) values (1500, '2020-02-10', '2022-03-10', 'ALIMENTACAO', '010101-1');
insert into despesas (valor, data_pagamento, data_pagamento_esperado, tipo_despesa, conta) values (3000, '2022-01-05', '2022-01-10', 'EDUCACAO', '020202-1');
insert into despesas (valor, data_pagamento, data_pagamento_esperado, tipo_despesa, conta) values (1850, '2022-01-10', '2022-01-10', 'TRANSPORTE', '030303-1');

insert into receitas (valor, data_recebimento, data_recebimento_esperado, descricao, conta, tipo_receita) values (2250, '2021-06-10', '2021-06-05', 'deposito em conta', '765097-4', 'SALARIO');
insert into receitas (valor, data_recebimento, data_recebimento_esperado, descricao, conta, tipo_receita) values (3000, '2021-05-10', '2021-05-05', 'transferencia', '765097-4', 'PRESENTE');
insert into receitas (valor, data_recebimento, data_recebimento_esperado, descricao, conta, tipo_receita) values (3000, '2021-04-10', '2021-04-05', 'pix', '765097-4', 'PREMIO');
