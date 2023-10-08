create table wishlist
(
    id           serial
        primary key,
    product_id   integer not null,
    user_id      integer
        unique
        constraint fktrd6335blsefl2gxpb8lr0gr7
            references users,
    created_date timestamp(6)
);

alter table wishlist
    owner to postgres;

