create table T_MAN_DOCUMENTS
(
    id         uniqueidentifier not null,
    student_id uniqueidentifier not null,
    identifier varchar(255)     not null,
    type       varchar(255) check (type in ('ID_CARD', 'STUDENT_ID_CARD', 'TAX_ID_CARD')),
    primary key (id)
);
create table T_MAN_MANAGERS
(
    id uniqueidentifier not null,
    primary key (id)
);
create table T_MAN_REQUESTS
(
    assignee_id   uniqueidentifier,
    id            uniqueidentifier not null,
    requesterUuid uniqueidentifier not null,
    comment       varchar(255),
    description   varchar(255),
    requestType   varchar(255)     not null check (requestType in ('FAIRNESS', 'DISMISSAL', 'SUBJECT', 'OTHER')),
    status        varchar(255)     not null check (status in
                                                   ('NEW', 'IN_PROGRESS', 'REQUESTER_FEEDBACK', 'IN_REVIEW', 'ACCEPTED',
                                                    'DENIED')),
    primary key (id)
);
create table T_MAN_STUDENTS
(
    degree varchar(255)     not null check (degree in ('COMPSCI_BSC', 'ELECTR_BSC', 'COMPSCI_MSC', 'ELECTR_MSC')),
    id     uniqueidentifier not null,
    primary key (id)
);
create table T_MAN_TEACHERS
(
    id uniqueidentifier not null,
    primary key (id)
);
create table T_MAN_USERS
(
    dateOfBirth           date,
    id                    uniqueidentifier not null,
    managerInformation_id uniqueidentifier,
    studentInformation_id uniqueidentifier,
    teacherInformation_id uniqueidentifier,
    firstName             varchar(255)     not null,
    lastName              varchar(255)     not null,
    locationOfBirth       varchar(255),
    saturnCode            varchar(255)     not null,
    primary key (id)
);
create unique nonclustered index UK_ea588t6tov975d7s60cw9ow2h on T_MAN_USERS (managerInformation_id) where managerInformation_id is not null;
create unique nonclustered index UK_s9smgexlc3lwhnnsi1640mxwx on T_MAN_USERS (studentInformation_id) where studentInformation_id is not null;
create unique nonclustered index UK_94mi2vel7nn7skrp1k0r1anos on T_MAN_USERS (teacherInformation_id) where teacherInformation_id is not null;
alter table T_MAN_DOCUMENTS
    add constraint FKjm5x4ikuul4er40a2kmwm02bw foreign key (student_id) references T_MAN_STUDENTS;
alter table T_MAN_REQUESTS
    add constraint FKk86kcq1xm4pjvynqpej0nt2en foreign key (assignee_id) references T_MAN_MANAGERS;
alter table T_MAN_USERS
    add constraint FKngluismbx4ugxd2bbb2mmmpt6 foreign key (managerInformation_id) references T_MAN_MANAGERS;
alter table T_MAN_USERS
    add constraint FK1j1edjq6jm07bt08ne9h9a6jv foreign key (studentInformation_id) references T_MAN_STUDENTS;
alter table T_MAN_USERS
    add constraint FKgs1dhmt2vr3jnhmjj33db79i5 foreign key (teacherInformation_id) references T_MAN_TEACHERS;
