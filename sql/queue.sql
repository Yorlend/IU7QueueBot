-- IU7 Students queue database

create table queue(
    task        text not null,
    surname     text not null,
    name        text not null,
    submitted   timestamp default now(),
    constraint pk_student primary key (task, surname)
);

create table schedule(
    task    text not null,
    surname text not null,
    constraint fk_queue foreign key (task, surname) references queue(task, surname)
);
