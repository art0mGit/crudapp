CREATE TABLE IF NOT EXISTS clients
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    email VARCHAR(254) NOT NULL ,
    phone VARCHAR(50)  NOT NULL ,
    created DATE NOT NULL
);