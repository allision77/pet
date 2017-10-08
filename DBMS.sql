set names utf8;
drop database if exists  Pethospital;
CREATE DATABASE Pethospital CHARACTER SET utf8 COLLATE utf8_general_ci ;
use Pethospital;
create table t_user
	(
    userId int primary key AUTO_INCREMENT,-- 自动编号
    userrole varchar(8) not null,
	userName varchar(32) not null,
	userpwd varchar(32) not null,
	usertel varchar(16), 
	useraddress varchar(255)
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;


	create table t_vet
		(
		vetid int primary key AUTO_INCREMENT,
		vetname varchar(32) not null
		
		)ENGINE=InnoDB DEFAULT CHARSET=utf8;
		create table t_speciality
			(
			specialityvetid int primary key AUTO_INCREMENT,
			specialityname varchar(32) not null
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
   create table t_vet_speciality
   	(
	vetid int not null, 
	specId int not null,
	foreign key(vetid) references t_vet(vetid)
	)ENGINE=InnoDB DEFAULT CHARSET=utf8;
    create  table   t_pet
    (
    petid int primary key AUTO_INCREMENT,
  petname varchar(32) not null,
  petbirthdate varchar(16) not null,
  petphoto varchar(64) not null, 
  petownerId int not null,
foreign key(petownerId) references t_user(userId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table t_visit
	(
	visitId int primary key AUTO_INCREMENT,
	visitpetId int not null,  
	visitvetId int not null,
	visitdate varchar(10) not null,
	visitdescription text not null,
	visittreatment text not null,
	foreign key(visitpetId) references t_pet(petId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;






