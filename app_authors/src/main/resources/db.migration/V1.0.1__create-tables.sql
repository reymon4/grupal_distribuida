create table authors (
                         id serial primary key,
                         first_name varchar(64) not null,
                         last_name varchar(64) not null
);

create table books(
                      id serial primary key,
                      isbn varchar(64) not null,
                      title varchar(64) not null,
                      price decimal(5,2) not null,
                      author_id integer not null,
                      foreign key (author_id) references authors(id)
);