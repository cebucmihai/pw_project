create table users
(
    id         serial
        primary key,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255)
);

alter table users
    owner to postgres;

