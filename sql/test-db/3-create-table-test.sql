\c job_search_test;

CREATE TABLE IF NOT EXISTS contact
(
    id        SERIAL PRIMARY KEY,
    telephone VARCHAR(20),
    email     VARCHAR(255) NOT NULL,
    skype     VARCHAR(50)
);

CREATE TYPE gender_type as enum ('FEMALE', 'MALE');

CREATE TABLE IF NOT EXISTS country
(
    id   SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS employee_personal_info
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(30) NOT NULL,
    surname  VARCHAR(50),
    birthday DATE,
    gender   gender_type,
    country  INTEGER REFERENCES country (id),
    city     VARCHAR(100)
);

CREATE TYPE lang_level as enum ('A1', 'A2', 'B1', 'B2', 'C1', 'C2');

CREATE TABLE IF NOT EXISTS language
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30)
--     level lang_level
);

CREATE TABLE IF NOT EXISTS specialization_type
(
    id   SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(128)
);

CREATE TYPE schedule_type as enum ('REMOTE_JOB', 'FULL_DAY', 'PART_TIME');
CREATE TYPE currency_type as enum ('USD', 'EUR', 'BYN');

CREATE TABLE IF NOT EXISTS job_preference
(
    id                SERIAL PRIMARY KEY,
    position          VARCHAR(255),
    salary            INTEGER,
    currency          currency_type,
    specialization_id INTEGER REFERENCES specialization_type (id),
    schedule          schedule_type,
    experience        INTEGER
);

CREATE TYPE user_role as enum ('ADMIN', 'EMPLOYEE', 'EMPLOYER');

CREATE TABLE IF NOT EXISTS usr
(
    id       SERIAL PRIMARY KEY,
    role     user_role           NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password CHAR(64)            NOT NULL
);

CREATE TABLE resume_languages
(
--     resume_id   INTEGER REFERENCES resume (id),
    language_id INTEGER REFERENCES language (id),
    id          SERIAL PRIMARY KEY,
    level       lang_level
);

CREATE TABLE IF NOT EXISTS resume
(
    id                SERIAL PRIMARY KEY,
    update            TIMESTAMP DEFAULT current_timestamp,
    prof_description  TEXT,

    usr_id            INTEGER REFERENCES usr (id),
    contact_id        INTEGER REFERENCES contact (id),
    personal_info_id  INTEGER REFERENCES employee_personal_info (id),
    job_preference_id INTEGER REFERENCES job_preference (id),
    language_id       INTEGER REFERENCES resume_languages (id)
);


CREATE TABLE employee
(
    user_id   INTEGER REFERENCES usr (id),
    resume_id INTEGER REFERENCES resume (id),
    PRIMARY KEY (user_id)
);

CREATE TABLE employer
(
    user_id      INTEGER REFERENCES usr (id),
    company_name VARCHAR(50)  UNIQUE,
    country      INTEGER REFERENCES country (id),
    city         VARCHAR(100),
    contact_id   INTEGER REFERENCES contact (id),
    PRIMARY KEY (user_id)
);

CREATE TABLE vacancy
(
    id                SERIAL PRIMARY KEY,
    position          VARCHAR(255) NOT NULL,
    city              VARCHAR(100) NOT NULL,
    salary            INTEGER,
    schedule          schedule_type,
    currency          currency_type,
    duties            TEXT         NOT NULL,
    requirements      TEXT         NOT NULL,
    country_id        INTEGER REFERENCES country (id),
    specialization_id INTEGER REFERENCES specialization_type (id),
    employer_id       INTEGER REFERENCES employer (user_id)
);

CREATE TABLE employee_vacancies
(
    id          SERIAL PRIMARY KEY,
    employee_id INTEGER REFERENCES employee (user_id),
    vacancy_id  INTEGER REFERENCES vacancy (id)
);


