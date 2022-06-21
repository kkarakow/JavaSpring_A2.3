CREATE TABLE plant(
	plantName VARCHAR(255),
	plantType VARCHAR(255),
	dateOfProduction DATE(10),
	nameOfSupplier VARCHAR(255),
	quantity INT(5),
	amountPaid DOUBLE(5)
);

CREATE TABLE plantTypesList(
	plantType VARCHAR(255)
);