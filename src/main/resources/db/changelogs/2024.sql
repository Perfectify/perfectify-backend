--liquibase formatted sql

--changeset fbullik:initial
create schema if not exists perfectify;

create table if not exists user_account
(
    firebase_uid     text primary key         not null,
    email            text unique              not null,
    sign_in_provider text                     not null,
    first_name       text                     not null,
    last_name        text                     not null,
    modified         timestamp with time zone not null,
    created          timestamp with time zone not null
);

--changeset fbullik:plant_entity
create table if not exists plant
(
    id       uuid primary key         not null,
    user_id  text                     not null,
    name     text                     not null,
    modified timestamp with time zone not null,
    created  timestamp with time zone not null,
    constraint fk_plant_user foreign key (user_id) references user_account (firebase_uid)
);

--changeset fbullik:watering_history_entity
create table if not exists watering
(
    id         uuid primary key         not null,
    plant_id   uuid                     not null,
    watered_at timestamp with time zone not null,
    created    timestamp with time zone not null
);

--changeset fbullik:add_scientific_name_to_plant_entity
alter table plant
    add column scientific_name text not null default '';

--changeset fbullik:add_watering_schedules
create table if not exists watering_schedule
(
    id              uuid primary key         not null,
    plant_id        uuid                     not null,
    interval_type   text,
    interval_amount bigint,
    day_of_week     text,
    week_of_month   int,
    modified        timestamp with time zone not null,
    created         timestamp with time zone not null,
    constraint fk_watering_schedule_plant foreign key (plant_id) references plant (id),
    constraint check_watering_schedule_pairs
        check (
            (interval_type is not null and interval_amount is not null)
                or
            (day_of_week is not null)
            )
);