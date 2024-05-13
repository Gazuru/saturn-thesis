create table T_EDU_COURSE_REGISTRATIONS
(
    COURSE_ID   uniqueidentifier not null,
    studentUuid uniqueidentifier not null,
    primary key (COURSE_ID, studentUuid)
);
create table T_EDU_COURSE_TEACHERS
(
    COURSE_ID   uniqueidentifier not null,
    teacherUuid uniqueidentifier not null,
    primary key (COURSE_ID, teacherUuid)
);
create table T_EDU_COURSES
(
    startTime  time             not null,
    length     bigint           not null,
    id         uniqueidentifier not null,
    subject_id uniqueidentifier not null,
    courseType varchar(255)     not null check (courseType in ('LECTURE', 'PRACTICE', 'LABORATORY', 'EXAM')),
    dayOfWeek  varchar(255)     not null check (dayOfWeek in
                                                ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY',
                                                 'SUNDAY')),
    location   varchar(255),
    name       varchar(255)     not null,
    primary key (id)
);
create table T_EDU_SEMESTER_REGISTRATIONS
(
    SEMESTER_ID uniqueidentifier not null,
    studentUuid uniqueidentifier not null,
    status      varchar(255)     not null check (status in ('INACTIVE', 'PASSIVE', 'ACTIVE', 'FINISHED')),
    primary key (SEMESTER_ID, studentUuid)
);
create table T_EDU_SEMESTERS
(
    semesterEnd   date             not null,
    semesterStart date             not null,
    id            uniqueidentifier not null,
    periodOfYear  varchar(255)     not null check (periodOfYear in ('SPRING', 'AUTUMN')),
    primary key (id)
);
create table T_EDU_SUBJECT_DEPUTIES
(
    SUBJECT_ID  uniqueidentifier not null,
    teacherUuid uniqueidentifier not null,
    primary key (SUBJECT_ID, teacherUuid)
);
create table T_EDU_SUBJECT_REGISTRATIONS
(
    grade                            bigint,
    SUBJECT_ID                       uniqueidentifier not null,
    semesterRegistration_SEMESTER_ID uniqueidentifier,
    semesterRegistration_studentUuid uniqueidentifier,
    studentUuid                      uniqueidentifier not null,
    primary key (SUBJECT_ID, studentUuid)
);
create table T_EDU_SUBJECTS
(
    archived                 bit              not null,
    creditIndex              bigint           not null,
    id                       uniqueidentifier not null,
    registerablePeriodOfYear varchar(255) check (registerablePeriodOfYear in ('SPRING', 'AUTUMN')),
    primary key (id)
);
alter table T_EDU_COURSE_REGISTRATIONS
    add constraint FKda6sp9qwawj0cpeuvr3isgkwx foreign key (COURSE_ID) references T_EDU_COURSES;
alter table T_EDU_COURSE_TEACHERS
    add constraint FKaab0hla781nu1cv83nut90l2r foreign key (COURSE_ID) references T_EDU_COURSES;
alter table T_EDU_COURSES
    add constraint FKby2ohw5ojck2rv10euxn0j0fv foreign key (subject_id) references T_EDU_SUBJECTS;
alter table T_EDU_SEMESTER_REGISTRATIONS
    add constraint FK6rijf16mfdeilq0ix3o26ssa4 foreign key (SEMESTER_ID) references T_EDU_SEMESTERS;
alter table T_EDU_SUBJECT_DEPUTIES
    add constraint FK4x9cp7xk932wmj2eafdwxh5bp foreign key (SUBJECT_ID) references T_EDU_SUBJECTS;
alter table T_EDU_SUBJECT_REGISTRATIONS
    add constraint FK403qxwiu5wp5qp3rp3snc5rwb foreign key (SUBJECT_ID) references T_EDU_SUBJECTS;
alter table T_EDU_SUBJECT_REGISTRATIONS
    add constraint FKjk3ux0vityb2w8ynbbq73lv50 foreign key (semesterRegistration_SEMESTER_ID, semesterRegistration_studentUuid) references T_EDU_SEMESTER_REGISTRATIONS;
