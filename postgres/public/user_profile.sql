create table user_profile
(
    id         serial
        primary key,
    email      varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    username   varchar(255)
);

alter table user_profile
    owner to postgres;

