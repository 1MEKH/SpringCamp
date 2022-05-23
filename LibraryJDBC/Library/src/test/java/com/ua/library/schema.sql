DROP TABLE IF EXISTS authors CASCADE;

CREATE TABLE authors
(
    author_id BIGINT PRIMARY KEY,
    surname VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS genres CASCADE;

CREATE TABLE genres
(
    genre_id BIGINT PRIMARY KEY,
    type VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE books
(
    book_id BIGINT PRIMARY KEY,
    book_name VARCHAR(255) NOT NULL,

    author_id BIGINT REFERENCES authors(author_id) NOT NULL,
    genre_id BIGINT REFERENCES genres(genre_id) NOT NULL
);