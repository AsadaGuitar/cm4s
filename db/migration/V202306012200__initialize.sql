SET AUTOCOMMIT = OFF;

drop table if exists client_profile;

create table client_profile
(
    client_id   varchar(4)  not null primary key,
    client_name varchar(32) not null,
    created_at  datetime    not null,
    closed_at   datetime default null
);

drop table if exists user_id_mapping_client_id;

create table user_id_mapping_client_id
(
    user_id    varchar(32) not null primary key,
    client_id  varchar(4)  not null,
    created_at datetime    not null,
    closed_at  datetime default null
);

drop table if exists user_profile;

create table user_profile
(
    user_id                      varchar(32)  not null primary key,
    encrypted_user_name          varchar(255) not null,
    encrypted_user_email_address varchar(255) not null,
    hashed_user_password         varchar(255) not null,
    created_at                   datetime     not null,
    closed_at                    datetime default null
);

commit;