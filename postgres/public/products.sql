create table products
(
    category_id integer          not null
        constraint fkog2rp4qthbtt2lfyhfo32lsw9
            references categories,
    id          serial
        primary key,
    price       double precision not null,
    description varchar(255)     not null,
    imageurl    varchar(255)     not null,
    name        varchar(255)     not null
);

alter table products
    owner to postgres;

