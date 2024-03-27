CREATE TABLE IF NOT EXISTS category (
    categoryID SERIAL PRIMARY KEY,
    category_type VARCHAR(255) NOT NULL,
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS _user (
    userID SERIAL PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    udpated_at TIMESTAMP,
    CONSTRAINT user_email_unique UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS budget (
    budgetID SERIAL PRIMARY KEY,
    user_id INTEGER,
    category_id INTEGER,
    amount BIGINT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (user_id) REFERENCES _user(userID),
    FOREIGN KEY (category_id) REFERENCES category(categoryID)
);

CREATE TABLE IF NOT EXISTS "transaction" (
    transactionID SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES _user(userID),
    amount BIGINT,
    date DATE,
    note VARCHAR(255),
    category_id INTEGER REFERENCES category(categoryID)
);