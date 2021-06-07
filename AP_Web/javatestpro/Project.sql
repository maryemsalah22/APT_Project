
use Project;
create table URLs
(
noOfDocument int,
URL varchar(1000) not null,
title varchar(1000) not null,
description varchar(8000),

primary key (noOfDocument)
);

create table Frequencies
(
word varchar(25),
noOfDocument int,
TF int not null,

primary key (word,noOfDocument),
foreign key (noOfDocument) references URLs(noOfDocument) ON DELETE CASCADE

);

create table Searched_Words
(
id int IDENTITY(1,1) PRIMARY KEY,
word varchar(100) NOT NULL UNIQUE ,
Rank1  int NOT NULL
);

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
insert into URLs values(1,'https://stackoverflow.com/questions/28991391','no title','no discription');
insert into URLs values(2,'https://stackoverflow.com','no title','no discription');

insert into Frequencies values('run',1,10);
insert into Frequencies values('run',2,5);



