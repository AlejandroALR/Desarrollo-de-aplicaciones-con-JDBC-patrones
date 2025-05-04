
CREATE DATABASE IF NOT EXISTS tarea2dwes;
USE tarea2dwes;

CREATE TABLE Planta (
    codigo VARCHAR(10) NOT NULL,
    nombreComun VARCHAR(100),
    nombreCientifico VARCHAR(150),
    PRIMARY KEY (codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Ejemplar (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    fk_planta VARCHAR(10),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_planta) REFERENCES Planta(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Credenciales (
    id INT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(50),
    password VARCHAR(100),
    fk_idPersona INT,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Persona (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100),
    email VARCHAR(150),
    fk_idCredenciales INT,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_idCredenciales) REFERENCES Credenciales(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Mensaje (
    id INT NOT NULL AUTO_INCREMENT,
    fechaHora DATETIME,
    mensaje VARCHAR(500),
    fk_idPersona INT,
    fk_idEjemplar INT,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_idPersona) REFERENCES Persona(id),
    FOREIGN KEY (fk_idEjemplar) REFERENCES Ejemplar(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;
