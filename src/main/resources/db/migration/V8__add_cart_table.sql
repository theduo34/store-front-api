

create table cart_items
(
    id bigint auto_increment primary key,
    cart_id    binary(16)    not null,
    product_id bigint        not null,
    quantity   int default 1 not null,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    constraint cart_items_carts_products_unique unique (cart_id, product_id),
    constraint cart_items_carts_id_fk foreign key (cart_id) references carts (id) on delete cascade,
    constraint cart_items_products_id_fk foreign key (product_id) references products (id) on delete cascade
);
