CREATE DATABASE cineplay;

CREATE TABLE cinema (
    idCinema INT NOT NULL AUTO_INCREMENT,
    nomeCinema VARCHAR(100),
    endereco VARCHAR(200),
    cnpj VARCHAR(20),
    senha VARCHAR(20),
    PRIMARY KEY (idCinema)
);

CREATE TABLE filme (
    idFilme INT NOT NULL,
    idCinema INT,
    titulo VARCHAR(100),
    descricao TEXT,
    classificacao VARCHAR(10),
    numeroPoltronas INT,
    poltronasAlugadas INT,
    preco FLOAT,
    dataInicio DATE,
    dataTermino DATE,
    PRIMARY KEY (idFilme),
    FOREIGN KEY (idCinema) REFERENCES cinema(idCinema)
);
ALTER TABLE filme
DROP COLUMN poltronasAlugadas;

ALTER TABLE filme
ADD COLUMN numeroPoltronas INT;

alter table cinema
drop column senha;

alter table cinema
add column senha varchar(500);

SELECT senha FROM cinema WHERE idCinema;