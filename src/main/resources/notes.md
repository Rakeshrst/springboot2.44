create table user (id integer not null, date_of_birth timestamp, name varchar(255), primary key (id))

create table post (id integer not null, description varchar(255), user_id integer, primary key (id))