create table T_EDU_EXAMS
(
    examDate   date             not null,
    startTime  time             not null,
    id         uniqueidentifier not null,
    subject_id uniqueidentifier not null,
    location   varchar(255),
    primary key (id)
);

create table T_EDU_EXAM_REGISTRATIONS
(
    grade       bigint,
    EXAM_ID     uniqueidentifier not null,
    studentUuid uniqueidentifier not null,
    primary key (EXAM_ID, studentUuid)
);

alter table T_EDU_EXAM_REGISTRATIONS
    add constraint FKEXAMID foreign key (EXAM_ID) references T_EDU_EXAMS;

alter table T_EDU_EXAMS
    add constraint FKSUBJID foreign key (subject_id) references T_EDU_SUBJECTS;

