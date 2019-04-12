
DROP TABLE IF EXISTS author;
CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birth_date DATE NOT NULL
);

INSERT INTO author (first_name, last_name, birth_date) VALUES
    ('J.R.R.', 'Tolkien', '1982-01-03'),
    ('Douglas', 'Adams', '1952-03-11'),
    ('George R. R.', 'Martin', '1948-09-20'),
    ('Frank', 'Herbert', '1920-10-08')
;