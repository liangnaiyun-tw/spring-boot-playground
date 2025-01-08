create database if not exists blog;

use blog;

create table if not exists User (
    Id int not null auto_increment,
    Username varchar(50) not null,
    Email varchar(255) not null,
    Password varchar(255) not null,
    Description text,
    ProfilePicture varchar(255),
    primary key(Id)
);


create table if not exists Post(
    Id int not null auto_increment,
    UserId int not null,
    Title varchar(255) not null,
    Content text,
    Category varchar(100),
    PublicationDate timestamp,
    Tags varchar(255),
    primary key(Id),
    foreign key(UserId) references User(Id)
);

create table if not exists Comment(
    Id int not null auto_increment primary key,
    PostId int not null,
    UserId int not null,
    Content text,
    CreateDate timestamp,
    foreign key (PostId) references Post(Id),
    foreign key (UserId) references User(Id)
);

create table if not exists Interaction(
    Id int not null auto_increment primary key,
    UserId int not null,
    PostId int not null,
    Type int not null,
    CreateDate timestamp not null,
    foreign key(UserId) references User(Id),
    foreign key(PostId) references Post(Id)
);