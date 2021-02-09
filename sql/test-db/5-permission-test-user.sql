CREATE USER job_user WITH PASSWORD '12345';
\c job_search_test;

GRANT CONNECT
    ON DATABASE job_search_test
    TO job_user;

GRANT SELECT, INSERT, UPDATE, DELETE
    ON ALL TABLES IN SCHEMA public
    TO job_user;

GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO job_user;