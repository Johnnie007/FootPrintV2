CREATE table user(
    id int auto_increment,
    month_joined varChar (10),
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
    vehicleGHG DECIMAL(10, 1),
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);

CREATE TABLE home(
    id int auto_increment,
    homeType varChar(10),
    homeSize int,
    homeGHG DECIMAL(10, 1),
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
    CCS DECIMAL(10, 1),
    primary key(id)
);

CREATE TABLE offSetters(
    id int auto_increment,
     month_added varChar(10),
    type varChar(15),
    product varChar(30),
    CCS DECIMAL(10, 1),
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);

CREATE TABLE profileImage(
    id int auto_increment,
    type varChar(15),
    imageName varChar(30),
    imageData LONGBLOB,
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);

CREATE TABLE GHGStorage(
    id int auto_increment,
    vehicleTotal DECIMAL(10, 1),
    homeTotal DECIMAL(10, 1),
    storageMonth varChar(10),
    userId int,
    primary key(id),
    FOREIGN Key (userId)
        REFERENCES user(id)
);