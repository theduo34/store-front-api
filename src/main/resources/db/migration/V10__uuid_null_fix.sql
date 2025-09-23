alter table users
    modify uuid binary(16) default ((uuid_to_bin(uuid()))) not null;

