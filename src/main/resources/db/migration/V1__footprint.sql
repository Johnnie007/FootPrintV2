CREATE table user(
    id int auto_increment,
    first_name varChar(30),
    last_name varChar(30),
    email varChar(20),
    password varChar(20)
    footprint varChar(30),
    lifeStyle varChar(40),
    primary key(id)
);

create table vehicle(
    id int auto_increment,
    type varChar(10).
    mpg DECIMAL(3, 2) NOT NULL,
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(Id)
);

create table home(
    id int auto_increment,
    homeType varChar(10),
    homeSize int
    userId int,
        primary key(id),
        FOREIGN Key (userId)
            REFERENCES user(Id)
);

create table recommendationList(
    id int auto_increment,
    type varChar(15),
    product varChar(30),
    productLocation varChar(30),
    primary key(id)
)
