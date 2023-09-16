create sequence hibernate_sequence;

create table student
(
    id                    bigint not null primary key,
    full_name             varchar,
    department_id         bigint,
    started_at            timestamp,
    education_level       varchar,
    course_work_id        bigint,
    graduate_work_id      bigint,
    end_at                timestamp,
    successfully_graduate boolean
);

create table department
(
    id                 bigint not null primary key,
    name               varchar,
    faculty            varchar,
    head_of_department varchar
);

create table course_work
(
    id    bigint not null primary key,
    name  varchar,
    grade int
);

create table graduate_work
(
    id    bigint not null primary key,
    name  varchar,
    grade int
);