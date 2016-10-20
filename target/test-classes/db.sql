/* MySQL database initializer for the JPA ORM used in this case */
/* Also, included, the values we expect for the tests */

CREATE DATABASE jpa;

CREATE TABLE groups (
	id INTEGER,
	group_name VARCHAR(40) NOT NULL UNIQUE,
	PRIMARY KEY (id)
);

INSERT INTO groups (id, group_name)VALUES (1, 'Grupo A');

CREATE TABLE people (
	id INTEGER,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	group_id INTEGER NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO people (id, first_name, last_name, group_id)
VALUES (1, 'Unai', 'Perez', NULL), (2, 'Iker', 'Perez', 1), (3, 'Leire', 'Urrutia', NULL);