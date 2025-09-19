alter table users
    add role varchar(20) not null default 'CUSTOMER';

alter table users
    change name first_name varchar(255) not null;

alter table users
    add last_name varchar(255) not null;

alter table users
    add user_uuid BINARY(16) not null;

alter table users
    add date_created DATE not null default (CURRENT_DATE);

alter table users
    add date_updated DATE not null default (CURRENT_DATE);
