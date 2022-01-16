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
