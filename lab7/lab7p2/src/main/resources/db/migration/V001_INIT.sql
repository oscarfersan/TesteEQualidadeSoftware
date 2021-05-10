CREATE TABLE student(
    id BIGSERIAL PRIMARY KEY,
    firstname varchar(255) not null,
    surname varchar(255) not null,
    course varchar(255)
);