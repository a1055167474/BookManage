create table people
(
	id INT auto_increment,
	name VARCHAR(255) null,
	age int null,
	constraint people_pk
		primary key (id)
);