BEGIN TRANSACTION;

/* select rows with the highest average salary order doesn't matter */
create table professor( id integer auto_increment, name varchar(200),dep_id integer, salary float, PRIMARY KEY(id));



/* Create few records in this table */
INSERT INTO professor VALUES(1,'Tom',1,22606);
INSERT INTO professor VALUES(2,'Zeynep',3,9287);
INSERT INTO professor VALUES(3,'Kristi',4,18870);
INSERT INTO professor VALUES(4,'Keanu',5,27524);
INSERT INTO professor VALUES(5,'Ahmet',1,26200);
INSERT INTO professor VALUES(6,'Osman',2,20067);
INSERT INTO professor VALUES(7,'Osman',1,7249);
INSERT INTO professor VALUES(8,'Osman',1,13437);
INSERT INTO professor VALUES(9,'Osman',3,28432);
INSERT INTO professor VALUES(10,'Osman',5,12610);
COMMIT;



create table department( id integer auto_increment, depname varchar(200), PRIMARY KEY(id) );
insert into department values(1,"CENG");

insert into department values(2,"MATH");

insert into department values(3,"BA");
insert into department values(4,"BIO");

insert into department values(5,"GEO");
/* Display all the records from the table */
-- SELECT * FROM professor;
-- SELECT * FROM department;

select depname, avg_sal from (select depname,avg(salary) as avg_sal from department join professor where professor.dep_id = department.id group by depname order by avg_sal desc) as table1 where avg_sal = (select avg_sal from  (select depname,avg(salary) as avg_sal from department join professor where professor.dep_id = department.id group by depname order by avg_sal desc)  limit 1);
