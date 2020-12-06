\c job_search;

CREATE TABLE IF NOT EXISTS contact (
	id INTEGER PRIMARY KEY,
	telephone VARCHAR(20) NOT NULL,
	email VARCHAR(255) NOT NULL,
	skype VARCHAR(255) NOT NULL
);

CREATE TYPE IF NOT EXISTS gender_type as enum ('Female', 'Male');

CREATE TABLE IF NOT EXISTS country(
	id	INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_personal_info (
	id INTEGER PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	surname VARCHAR(255) NOT NULL,
	birthday DATE,
	gender gender_type,
	country INTEGER REFERENCES country(id),
	city VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS language ( 
	id INTEGER PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	level lang_level NOT NULL
);


CREATE TABLE IF NOT EXISTS specialization_type(
	id	INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(128) NOT NULL
);

CREATE TYPE schedule_type as enum ('remote_job', 'full_day', 'part_time');
CREATE TYPE currency_type as enum ('USD', 'EUR', 'BYN');

CREATE TABLE IF NOT EXISTS job_preference (
	id INTEGER PRIMARY KEY,
	desired_position VARCHAR(255) NOT NULL,
	salary INTEGER,
	currency currency_type,
	specialization_id INTEGER REFERENCES specialization_type(id),
	schedule schedule_type[],
	experience INTEGER
);

CREATE TYPE user_role as enum ('Admin', 'Employee', 'Employer');

CREATE TABLE IF NOT EXISTS usr (
	id INTEGER PRIMARY KEY,
	role user_role NOT NULL, 
	email VARCHAR(255) NOT NULL, 
	password VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS resume (
	id INTEGER PRIMARY KEY,
	update DATE NOT NULL,
	isRaised BOOLEAN NOT NULL,
	prof_description TEXT,
	
	usr_id INTEGER REFERENCES usr(id),
	contact_id INTEGER  REFERENCES contact(id) ,
	personal_info_id INTEGER  REFERENCES employee_personal_info(id),
	job_preference_id INTEGER REFERENCES job_preference(id)
);


CREATE TABLE resume_languages (
	resume_id INTEGER REFERENCES resume(id),
	language_id INTEGER  REFERENCES language(id),
	PRIMARY KEY (resume_id, language_id)
);

CREATE TABLE employee (
	user_id INTEGER  REFERENCES usr(id),
	resume_id INTEGER REFERENCES resume(id),
	PRIMARY KEY (resume_id, user_id)
);


CREATE TABLE vacancy (
	id INTEGER PRIMARY KEY,
	position VARCHAR(255) NOT NULL,
	city VARCHAR(255) NOT NULL,
	min_experience INTEGER NOT NULL,
	schedule schedule_type,
	duties TEXT NOT NULL,
	requirements TEXT NOT NULL,
	employer_id INTEGER  REFERENCES employer(id)
);

CREATE TABLE employer (
	id INTEGER PRIMARY KEY,
	company_name VARCHAR(255) NOT NULL,
	country INTEGER  REFERENCES country(id),
	city VARCHAR(255),
	contact_id INTEGER  REFERENCES contact(id)
);