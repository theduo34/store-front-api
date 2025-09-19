alter table users
    add constraint users_uuid_unique unique (user_uuid);

alter table users
    add constraint users_email_unique unique (email);
