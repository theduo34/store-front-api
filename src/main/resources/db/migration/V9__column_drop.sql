
alter table users
    change user_uuid uuid binary(16) default (uuid_to_bin(uuid())) not null;

alter table users
    change group_type group_type_id bigint default 1 not null;

