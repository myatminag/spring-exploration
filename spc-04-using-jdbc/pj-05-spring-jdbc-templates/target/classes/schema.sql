DROP TABLE division IF EXISTS;
DROP TABLE district IF EXISTS;
DROP TABLE township IF EXISTS;

CREATE TABLE division (
	id INT PRIMARY KEY,
	name VARCHAR(60) NOT NULL
);

CREATE TABLE district (
	id INT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	division_id INT NOT NULL,
	FOREIGN KEY (division_id) REFERENCES division(id)
);

CREATE TABLE township (
	id INT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	district_id INT NOT NULL,
	FOREIGN KEY (district_id) REFERENCES district(id)
);