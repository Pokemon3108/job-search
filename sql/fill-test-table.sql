INSERT INTO contact (id, telephone, email, skype)
VALUES (2, '+375295395676', 'parrot@gmail.com', 'parrot'),
       (3, '+74956789898', 'qwerty@outlook.com', 'pikachi34'),
       (4, '+375295989090', 'qwerty@gmail.com', 'login1456');

SELECT setval('contact_id_seq', (SELECT MAX(id) from contact));

INSERT INTO usr(id, role, email, password)
VALUES (1, 'EMPLOYEE', 'pokemon31@gmail.com', '0123456789012345678911111111111111111111111111111111111111111111'),
       (2, 'EMPLOYER', 'Dasha@tut.by', '0123456789012345678911111111111111111111111111111111111111111111'),
       (3, 'EMPLOYER', 'Dasha31@tut.by', '0123456789012345678911111111111111111111111111111111111111111111');

SELECT setval('usr_id_seq', (SELECT MAX(id) from usr));

INSERT INTO job_preference(id, salary, currency, schedule, experience, specialization_id, position)
VALUES (5, 234, 'USD', 'FULL_DAY', 10, 13, 'Java developer'),
       (6, 500, 'BYN', 'FULL_DAY', 2, 3, 'Teacher'),
       (7, 1000, 'EUR', 'REMOTE_JOB', 1, 2, 'Manager');

SELECT setval('job_preference_id_seq', (SELECT MAX(id) from job_preference));

INSERT INTO employee_personal_info(id, name, surname, birthday, gender, country, city)
VALUES (2, 'Darya', 'Zalevskaya', '31.08.2001', 'FEMALE', 13, NULL),
       (3, 'Tom', NULL, '12.09.1999', 'MALE', 14, 'Tbilisi'),
       (4, 'Witney', 'Jogish', '12.12.1987', 'FEMALE', 1, 'Moscow');

SELECT setval('employee_personal_info_id_seq', (SELECT MAX(id) from employee_personal_info));
