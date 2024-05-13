alter table T_EDU_COURSE_REGISTRATIONS
    drop constraint if exists FKda6sp9qwawj0cpeuvr3isgkwx;
alter table T_EDU_COURSE_TEACHERS
    drop constraint if exists FKaab0hla781nu1cv83nut90l2r;
alter table T_EDU_COURSES
    drop constraint if exists FKby2ohw5ojck2rv10euxn0j0fv;
alter table T_EDU_SEMESTER_REGISTRATIONS
    drop constraint if exists FK6rijf16mfdeilq0ix3o26ssa4;
alter table T_EDU_SUBJECT_DEPUTIES
    drop constraint if exists FK4x9cp7xk932wmj2eafdwxh5bp;
alter table T_EDU_SUBJECT_REGISTRATIONS
    drop constraint if exists FK403qxwiu5wp5qp3rp3snc5rwb;
alter table T_EDU_SUBJECT_REGISTRATIONS
    drop constraint if exists FKjk3ux0vityb2w8ynbbq73lv50;
drop table if exists T_EDU_COURSE_REGISTRATIONS;
drop table if exists T_EDU_COURSE_TEACHERS;
drop table if exists T_EDU_COURSES;
drop table if exists T_EDU_SEMESTER_REGISTRATIONS;
drop table if exists T_EDU_SEMESTERS;
drop table if exists T_EDU_SUBJECT_DEPUTIES;
drop table if exists T_EDU_SUBJECT_REGISTRATIONS;
drop table if exists T_EDU_SUBJECTS;
