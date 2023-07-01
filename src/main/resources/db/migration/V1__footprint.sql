CREATE table user(
    id int auto_increment,
    first_name varChar(30),
    last_name varChar(30),
    email varChar(50),
    diet varChar(30),
    lifeStyle varChar(40),
    primary key(id)
);

create table vehicle(
    id int auto_increment,
    make varChar(15),
    model varChar(15),
    vehicleYear int,
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(Id)
);