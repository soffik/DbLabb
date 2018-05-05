CREATE TABLE Person (
    ssn TEXT PRIMARY KEY,
    lastName TEXT,
    firstName TEXT,
    sex TEXT
);

CREATE TABLE Address (
    streetAddress TEXT NOT NULL,
    streetNumber TEXT,
    city TEXT,
    zipCode TEXT,
    addressType TEXT,
    ssn TEXT,
    FOREIGN KEY(ssn) REFERENCES Person(ssn)
);
