drop table employee;
create table employee
(
   emp_id integer not null,
   kits_id varchar(10) not null,
   name varchar(255) not null,
   primary key(emp_id)
);


drop table holiday_list;
create table holiday_list
(
   year integer not null,
   week_begin date not null,
   holiday_date date not null,
   primary key(year,week_begin,holiday_date)
);

drop table mismatch_report;
create table mismatch_report
(
   emp_id integer not null,
   kits_id varchar(10) not null,
   name varchar(255) not null,
   week_begin date not null,
   imps_count integer,
   trs_count integer,
   project_count integer,
   mismatch boolean,
   mismatch_desc varchar(255) not null,
   primary key(emp_id,week_begin)
);

