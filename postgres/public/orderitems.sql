create table orderitems
(
    order_item_id serial
        primary key,
    created_date  timestamp,
    order_id      integer,
    price         double precision,
    product_id    integer,
    quantity      integer
);

alter table orderitems
    owner to postgres;

