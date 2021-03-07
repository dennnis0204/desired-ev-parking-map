insert into users
    (id, email, name, provider)
values (1, 'john@gmail.com', 'john', 'google');

insert into users
    (id, email, name, provider)
values (2, 'dennnis0204@gmail.com', 'denis', 'google');

alter sequence users_id_seq restart with 100;