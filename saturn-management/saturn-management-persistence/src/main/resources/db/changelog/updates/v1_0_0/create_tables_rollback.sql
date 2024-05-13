alter table T_MAN_DOCUMENTS
    drop constraint if exists FKjm5x4ikuul4er40a2kmwm02bw;
alter table T_MAN_REQUESTS
    drop constraint if exists FKk86kcq1xm4pjvynqpej0nt2en;
alter table T_MAN_USERS
    drop constraint if exists FKngluismbx4ugxd2bbb2mmmpt6;
alter table T_MAN_USERS
    drop constraint if exists FK1j1edjq6jm07bt08ne9h9a6jv;
alter table T_MAN_USERS
    drop constraint if exists FKgs1dhmt2vr3jnhmjj33db79i5;
drop table if exists T_MAN_DOCUMENTS;
drop table if exists T_MAN_MANAGERS;
drop table if exists T_MAN_REQUESTS;
drop table if exists T_MAN_STUDENTS;
drop table if exists T_MAN_TEACHERS;
drop table if exists T_MAN_USERS;
