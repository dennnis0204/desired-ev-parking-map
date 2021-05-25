create sequence users_id_seq start 1 increment 1;
create sequence charging_points_id_seq start 1 increment 1;

create table users
(
    id          int8         not null,
    email       varchar(255) not null,
    image_url   varchar(255),
    name        varchar(255) not null,
    password    varchar(255),
    provider    varchar(255) not null,
    provider_id varchar(255),
    primary key (id)
);

create table charging_points
(
    id              int8         not null,
    latitude        numeric       not null,
    longitude       numeric       not null,
    power           varchar(255) not null,
    type_of_current varchar(255) not null,
    user_id         int8,
    primary key (id)
);

create table polish_cities
(
    id        int8   not null,
    latitude  float4 not null,
    longitude float4 not null,
    name      varchar(255),
    primary key (id)
);

alter table if exists users
    add constraint users_email unique (email);
alter table if exists charging_points
    add constraint charging_points_user_fk foreign key (user_id) references users;