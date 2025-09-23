create table carts
(
    id         binary(16) default ((uuid_to_bin(uuid()))) not null primary key,
    created_at datetime   default current_timestamp     not null
);

