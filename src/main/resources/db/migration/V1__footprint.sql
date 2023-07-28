CREATE table user(
    id int auto_increment,
    first_name varChar(30),
    last_name varChar(30),
    email varChar(20) UNIQUE,
    password varChar(255) not null,
    footprint int(30),
    lifeStyle varChar(40),
    primary key(id)
);

CREATE TABLE vehicle(
    id int auto_increment,
    type varChar(10),
    mpg varChar(5),
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);

CREATE TABLE home(
    id int auto_increment,
    homeType varChar(10),
    homeSize int,
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);

CREATE TABLE recommendationList(
    id int auto_increment,
    type varChar(15),
    product varChar(30),
    productLocation varChar(30),
    CCS integer,
    primary key(id)
);

CREATE TABLE offSetters(
    id int auto_increment,
    type varChar(15),
    product varChar(30),
    CCS int,
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);