/*const express = require('express'); //essa parte estou a importar o pacote express
const app = express(); //pegando o contrutor  do express porque e um objectoo
const porta = 5000; // informando a porta em uso
app.listen(porta, () => {
    console.log(`servidor rodando na porta: ${porta}`)
})*/

import express from 'express';

const app = express();

const porta = 5000; // informando a porta em uso

app.listen(porta,() => {
    console.log(`servidor rodando na porta: ${porta}`)
});