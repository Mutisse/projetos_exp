create database DMI_INF2Ano_TECH;  -- criancao do banco de dados 
 

 use DMI_INF2Ano_TECH;
 
 -- criar tabela de usiaros
CREATE TABLE tbUsuarios (
    Id_User INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(30) NOT NULL,
    Genero ENUM('Masculino', 'Feminino'),
    Username VARCHAR(30) UNIQUE,
    email VARCHAR(30) UNIQUE,
    biografia VARCHAR(50),
    estado ENUM('Activo', 'Inativo', 'Removido'),
    SenhaUser VARCHAR(50)
);
 select Max(Id_User) from tbUsuarios; -- tras o campo em questtao 
 describe tbUsuarios; -- para descrevar atabela
 select * from tbUsuarios;    -- criar uma  tabela 
 -- formatar a tabela no formato desejado
