CREATE DATABASE job_search;

CREATE USER job_user WITH PASSWORD '12345';

REVOKE CONNECT ON DATABASE job_search FROM PUBLIC;

GRANT CONNECT
    ON DATABASE job_search
    TO job_user;

REVOKE ALL
    ON ALL TABLES IN SCHEMA public
    FROM PUBLIC;

GRANT SELECT, INSERT, UPDATE, DELETE
    ON ALL TABLES IN SCHEMA public
    TO job_user;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO job_user;
