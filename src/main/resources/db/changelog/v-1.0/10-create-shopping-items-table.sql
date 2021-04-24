
create table shopping_items (
                                id bigserial primary key,
                                name varchar(255) not null,
                                user_id bigint
);

alter table shopping_items
    add constraint FKtpkopyby8qwu1noj4n4t3yuig
        foreign key (user_id)
            references users (id);