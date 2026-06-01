CREATE TABLE employee
(
    id         SERIAL PRIMARY KEY,
    firstname  VARCHAR,
    lastname   VARCHAR,
    email      VARCHAR,
    department VARCHAR,
    salary     NUMERIC(10, 2),
    active     BOOLEAN
);

CREATE TABLE intern
(
    id        SERIAL PRIMARY KEY,
    manager_id INT REFERENCES employee(id),
    firstname VARCHAR,
    lastname  VARCHAR,
    email     VARCHAR,
    school    VARCHAR,
    amount NUMERIC(10,2)
);