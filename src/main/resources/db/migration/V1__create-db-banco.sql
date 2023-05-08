create table cliente (
    id bigint auto_increment not null,
    nome varchar(255) not null,
	data_nascimento date not null,
	saldo decimal(20,4) not null,
	numero_conta varchar(100) not null,
	plano_exclusive boolean,
	primary key(id));

create table movimentacao(
    id_movimentacao bigint auto_increment not null,
    tipo varchar(100) not null,
    valor decimal(20,4) not null,
    data timestamp not null,
    valor_taxa_adm decimal(20,4),
    id_cliente bigint not null,
    primary key(id_movimentacao),
     foreign key (id_cliente) references cliente(id));

insert into cliente (nome, data_nascimento, saldo, numero_conta, plano_exclusive)
  values ('Jose Maria', '1988-10-30', 1000, '3456', false);
insert into movimentacao (tipo, valor, data, valor_taxa_adm, id_cliente) values (1, 100, '2023-05-07T22:52:05.012896', 0, 1);
insert into movimentacao (tipo, valor, data, valor_taxa_adm, id_cliente) values (0, 3500, '2023-05-07T20:52:05.012896', 5, 1);

insert into cliente (nome, data_nascimento, saldo, numero_conta, plano_exclusive)
  values ('Maria Jose d Silva', '1977-11-23', 1000, '3456', true);

