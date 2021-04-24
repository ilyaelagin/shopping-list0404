create table users (
                       id bigserial primary key,
                       password varchar(255) not null,
                       username varchar(255) unique not null
);

alter table users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
