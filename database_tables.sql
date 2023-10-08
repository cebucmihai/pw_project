create table categories
(
    id            serial
        primary key,
    category_name varchar(255),
    description   varchar(255),
    image_url     varchar(255)
);

alter table categories
    owner to postgres;

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

create table cart
(
    id           serial
        primary key,
    product_id   integer not null
        constraint fkpu4bcbluhsxagirmbdn7dilm5
            references products,
    quantity     integer not null,
    user_id      integer
        constraint fkg5uhi8vpsuy0lgloxk2h4w5o6
            references users,
    created_date timestamp(6)
);

alter table cart
    owner to postgres;

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

create table wishlist
(
    id           serial
        primary key,
    product_id   integer not null
        constraint fk6p7qhvy1bfkri13u29x6pu8au
            references products,
    user_id      integer
        unique
        constraint fktrd6335blsefl2gxpb8lr0gr7
            references users,
    created_date timestamp(6)
);

alter table wishlist
    owner to postgres;


