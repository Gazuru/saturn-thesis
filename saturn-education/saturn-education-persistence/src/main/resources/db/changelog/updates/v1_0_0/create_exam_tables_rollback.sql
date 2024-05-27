alter table T_EDU_EXAM_REGISTRATIONS
    drop constraint if exists FKEXAMID;

alter table T_EDU_EXAMS
    drop constraint if exists FKSUBJID;

drop table if exists T_EDU_EXAM_REGISTRATIONS;
drop table if exists T_EDU_EXAMS;
