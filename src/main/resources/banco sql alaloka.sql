-- Criando o banco de dados
DROP DATABASE IF EXISTS alalokaEstacionamento;
CREATE DATABASE alalokaEstacionamento;
USE alalokaEstacionamento;

-- Criação da tabela Cliente
CREATE TABLE Cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(15)
);

-- Criação da tabela Carro
CREATE TABLE Carro (
    id INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(8) UNIQUE NOT NULL,
    tipoCarro ENUM('carro', 'moto') NOT NULL
);

-- Criação da tabela Vaga
CREATE TABLE Vaga (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numeroVaga INT UNIQUE NOT NULL,
    tipoVaga ENUM('carro', 'moto', 'especial') NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE 
);

-- Criação da tabela Cancela
CREATE TABLE Cancela (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status ENUM('aberta', 'fechada') NOT NULL
);

-- Criação da tabela Ticket
CREATE TABLE Ticket (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idVaga INT,
    idCarro INT,
    idCliente INT,
    dataEntrada DATETIME NOT NULL,
    dataSaida DATETIME,
    valorPago DECIMAL(10, 2),
    FOREIGN KEY (idVaga) REFERENCES Vaga(id),
    FOREIGN KEY (idCarro) REFERENCES Carro(id),
    FOREIGN KEY (idCliente) REFERENCES Cliente(id)
);

select * from vaga;
select * from Cliente;
select * from Carro;