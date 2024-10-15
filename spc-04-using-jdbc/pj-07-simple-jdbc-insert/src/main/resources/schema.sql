CREATE TABLE product (
	id INT GENERATED BY DEFAULT AS IDENTITY,
	name VARCHAR(100) NOT NULL,
	category VARCHAR(40) NOT NULL,
	image VARCHAR(255),
	price INT NOT NULL,
	PRIMARY KEY (id)
);