alter table users
    alter column user_uuid set default ((uuid_to_bin(uuid())));

