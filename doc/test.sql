-- 测试表 user
create table user (
    id bigint not null,
    name varchar(32) not null comment 'username',
    email varchar(32) not null comment 'email',
    phone varchar(16) not null comment 'phone',
    password varchar(255) not null comment 'password',
    sex tinyint not null default 1 comment 'sex, 1-man, 2-woman, 3-other',
    avatar varchar(255) default null comment 'avatar',
    birthday date default null comment 'birthday',
    type tinyint default 1 not null comment 'user type,1-common user,2-system user',
    create_time datetime default current_timestamp not null comment 'create time',
    constraint user_pk primary key (id)
) DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci comment 'user';
create unique index user_idx_email on user (email);
create unique index user_idx_phone on user (phone);
