require('dotenv').config({path:'variaveis.env'});
const express= require('express'); 
const cors =require('cors');
const bodyparser=require('body-parser');

const routes = require('./routes');

const server = express();
server.use(cors());
server.use(bodyparser.urlencoded({extended:false}));
server.use('/api',routes)


const porta=process.env.PORT || 5000;

server.listen(porta,()=>console.log(`servidor rodando em:http://localhost:${porta} `));