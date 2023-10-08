create table cart
(
    id           serial
        primary key,
    product_id   integer not null,
    quantity     integer not null,
    user_id      integer
        constraint fkg5uhi8vpsuy0lgloxk2h4w5o6
            references users,
    created_date timestamp(6)
);

alter table cart
    owner to postgres;

