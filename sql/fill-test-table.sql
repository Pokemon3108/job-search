INSERT INTO contact (id, telephone, email, skype)
VALUES (2, '+375295395676', 'parrot@gmail.com', 'parrot'),
       (3, '+74956789898', 'qwerty@outlook.com', 'pikachi34'),
       (4, '+375295989090', 'qwerty@gmail.com', 'login1456'),
       (5, '+123456789090', 'zxcvb@mail.ru', NULL),
       (6, '+3752990909001', 'spring@mail.ru', 'winter1234'),
       (7, '+375443455454', 'qas@tut.by', 'spring0909');

SELECT setval('contact_id_seq', (SELECT MAX(id) from contact));

INSERT INTO usr(id, role, email, password)
VALUES (1, 'EMPLOYEE', 'pokemon31@gmail.com', '0123456789012345678911111111111111111111111111111111111111111111'),
       (2, 'EMPLOYER', 'Dasha@tut.by', '0123456789012345678911111111111111111111111111111111111111111111'),
       (3, 'EMPLOYER', 'Dasha31@tut.by', '0123456789012345678911111111111111111111111111111111111111111111'),
       (4, 'EMPLOYEE', 'employee@yandex.ru', 'qwertyuiop11111111111111111111111111111111111111111111'),
       (5, 'EMPLOYEE', 'employee2@gmail.com', '123456789011111111111111111111111111111111111111111111'),
       (6, 'EMPLOYER', 'epam@gmail.com', '0909909090123456789011111111111111111111111111111111111111111111'),
       (7, 'EMPLOYER', 'xpcomp@mail.ru', '0909909090123456789011111111111111111111111111111111111111111aaa'),
       (8, 'EMPLOYER', 'user@gmail.com', 'qa!!!asasa123456789011111111111111111111111111111111111111111111'),
       (9, 'EMPLOYEE', 'new_employee@mail.ru', 'qa!!!asasa123456789011111111111111111111111111111111111111111111');

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

INSERT INTO resume_languages (id, language_id, level)
VALUES (1, 2, 'B2'),
       (2, 3, 'B1'),
       (3, 6, 'A2');

SELECT setval('resume_languages_id_seq', (SELECT MAX(id) from resume_languages));


INSERT INTO resume(id, prof_description, usr_id, contact_id, personal_info_id, job_preference_id, language_id)
VALUES (1, 'Good', 1, 2, 2, 5, 1);

INSERT INTO resume (id, usr_id)
VALUES (2, 4),
       (3, 5);

SELECT setval('resume_id_seq', (SELECT MAX(id) from resume));

INSERT INTO employer(user_id, company_name, country, city, contact_id)
VALUES (3, 'Roboteka', 1, 'Moscow', 5),
       (6, 'Oracle', 45, NULL, 6),
       (7, 'Mouse', 15, 'New York', NULL),
       (8, 'CompSoft', 23, 'Munchen', NULL);

INSERT INTO employee(user_id, resume_id)
VALUES (1, 1),
       (4, 4),
       (5, 3);







