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

