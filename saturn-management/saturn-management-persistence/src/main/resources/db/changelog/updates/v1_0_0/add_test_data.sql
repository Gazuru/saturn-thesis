DECLARE @student_id_1 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1f'
DECLARE @student_id_2 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1f'
DECLARE @student_id_3 UNIQUEIDENTIFIER = N'51a2aee1-1b0e-4220-9b35-59c62791ca1f'
DECLARE @student_id_4 UNIQUEIDENTIFIER = N'41a2aee1-1b0e-4220-9b35-59c62791ca1f'
DECLARE @student_id_5 UNIQUEIDENTIFIER = N'31a2aee1-1b0e-4220-9b35-59c62791ca1f'

INSERT INTO T_MAN_STUDENTS (id, degree)
VALUES (@student_id_1, 'COMPSCI_MSC'),
       (@student_id_2, 'COMPSCI_BSC'),
       (@student_id_3, 'ELECTR_MSC'),
       (@student_id_4, 'ELECTR_BSC'),
       (@student_id_5, 'COMPSCI_MSC')

DECLARE @teacher_id_1 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1e'
DECLARE @teacher_id_2 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1d'
DECLARE @teacher_id_3 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1c'
DECLARE @teacher_id_4 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1b'
DECLARE @teacher_id_5 UNIQUEIDENTIFIER = N'71a2aee1-1b0e-4220-9b35-59c62791ca1a'

INSERT INTO T_MAN_TEACHERS (id)
VALUES (@teacher_id_1),
       (@teacher_id_2),
       (@teacher_id_3),
       (@teacher_id_4),
       (@teacher_id_5)

DECLARE @manager_id_1 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1e'
DECLARE @manager_id_2 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1d'
DECLARE @manager_id_3 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1c'
DECLARE @manager_id_4 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1b'
DECLARE @manager_id_5 UNIQUEIDENTIFIER = N'61a2aee1-1b0e-4220-9b35-59c62791ca1a'

INSERT INTO T_MAN_MANAGERS (id)
VALUES (@manager_id_1),
       (@manager_id_2),
       (@manager_id_3),
       (@manager_id_4),
       (@manager_id_5)

DECLARE @user_id_1  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a965'
DECLARE @user_id_2  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912b965'
DECLARE @user_id_3  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912c965'
DECLARE @user_id_4  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912d965'
DECLARE @user_id_5  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912e965'
DECLARE @user_id_6  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912f965'
DECLARE @user_id_7  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a865'
DECLARE @user_id_8  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a765'
DECLARE @user_id_9  UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a665' -- ADMIN
DECLARE @user_id_10 UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a565'
DECLARE @user_id_11 UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a465'
DECLARE @user_id_12 UNIQUEIDENTIFIER = N'1dfff77b-edef-4771-97ba-8d071912a365'


INSERT INTO T_MAN_USERS (id, managerInformation_id, studentInformation_id, teacherInformation_id,
                         firstName, lastName, locationOfBirth, saturnCode)
VALUES (@user_id_1, @manager_id_1, null, null, 'János', 'Menedzser', 'Budapest', 'manager1'),
       (@user_id_2, @manager_id_2, null, null, 'Péter', 'Menedzser', 'Budapest', 'manager2'),
       (@user_id_11, @manager_id_3, null, null, 'Ilona', 'Menedzser', 'Budapest', 'manager3'),
       (@user_id_12, @manager_id_4, null, null, 'Diána', 'Menedzser', 'Budapest', 'manager4'),
       (@user_id_3, null, @student_id_1, null, 'Levente', 'Diák', 'Budapest', 'student1'),
       (@user_id_4, null, @student_id_2, null, 'Ákos', 'Di', 'Budapest', 'student2'),
       (@user_id_10, null, @student_id_5, null, 'Gergely', 'Diák', 'Budapest', 'student3'),
       (@user_id_5, null, null, @teacher_id_1, 'Károly', 'Tanár', 'Budapest', 'teacher1'),
       (@user_id_6, null, null, @teacher_id_2, 'József', 'Tanár', 'Budapest', 'teacher2'),
       (@user_id_7, null, null, @teacher_id_3, 'Tamás', 'Tanár', 'Budapest', 'teacher3'),
       (@user_id_8, null, @student_id_3, @teacher_id_4, 'Gergő', 'Labvez', 'Budapest', 'studentteacher1'),
       (@user_id_9, @manager_id_5, @student_id_4, @teacher_id_5, 'Gábor', 'Gonda', 'Budapest', 'admin')

