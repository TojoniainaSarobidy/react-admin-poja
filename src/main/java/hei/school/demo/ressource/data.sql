INSERT INTO employee(firstname, lastname, email, department, salary, active)
VALUES ('Martin', 'Alice', 'alice@company.com', 'Informatique', 2800.0, true),
       ('Dupont', 'Bob', 'bob@company.com', 'Marketing', 2500.0, true),
       ('Nguyen', 'Clara', 'clara@company.com', 'RH', 2600, false);

INSERT INTO intern (manager_id, firstname, lastname, email, school, amount)
VALUES (1, 'Paul', 'Rajaonarivelo', 'paul@intern.com', 'HEI Madagascar', 0.00),
       (2, 'Emma', 'Rakotonirina', 'emma@intern.com', 'ISPM', 500.00),
       (1, 'Lucas', 'Andrianasolo', 'lucas@intern.com', 'IT University', 1200.00);