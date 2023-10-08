create table tokens
(
    id           serial
        primary key,
    user_id      integer not null
        unique
        constraint fk2dylsfo39lgjyqml2tbe0b0ss
            references users,
    created_date timestamp(6),
    token        varchar(255)
);

alter table tokens
    owner to postgres;

