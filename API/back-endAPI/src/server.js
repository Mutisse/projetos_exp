require('dotenv').config({path: 'config.env'}); // Importando dotenv para leitura de arquivo .env
const express = require('express'); // Importando o Express
const cors = require('cors'); // Importando o CORS
const bodyparser = require('body-parser'); // Importando o Body Parser

const routes = require('./routes'); // Importando as rotas definidas em './routes'

const server = express(); // Criando uma instância do Express
server.use(cors()); // Configurando o CORS para permitir solicitações de diferentes origens
server.use(bodyparser.urlencoded({extended: false})); // Configurando o Body Parser para lidar com solicitações codificadas em URL
server.use('/em', routes); // Definindo o prefixo '/em' para as rotas importadas do arquivo './routes'

const porta = process.env.PORT || 5000; // Lendo a porta do arquivo .env ou usando a porta 5000 como padrão

server.listen(porta, () => console.log(`Servidor rodando em: http://localhost:${porta}/em`)); // Iniciando o servidor na porta especificada

