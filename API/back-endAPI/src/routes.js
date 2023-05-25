const express = require('express');
const router = express.Router();

// Importando os módulo  responsáveis por controlar as operações relacionadas.
const clienteController = require('./controller/clienteController');
const postController = require('./controller/postController');
const objectController = require('./controller/objectController');

// Rota para buscar todos os clientes
router.get('/clientes', clienteController.buscaTodosClientes);

// Rota para buscar um cliente específico por ID
router.get('/cliente/:id', clienteController.buscaUmCliente);

// Rota para inserir um novo cliente
router.post('/cliente', clienteController.inseriCliente);

// Rota para atualizar os dados de um cliente existente por ID
router.put('/cliente/:id', clienteController.atulizaDadosDoCliente);

// Rota para excluir um cliente existente por ID
router.delete('/cliente/:id', clienteController.excluiCliente);


/* ====================== buscar post ======================= */
// Rota: GET /posts
// Descrição: Busca todos os posts.
router.get('/posts', postController.buscaTodosPosts);

// Rota: GET /buscarPost/:id
// Descrição: Busca um post por ID.
router.get('/buscarPost/:id', postController.buscaPostPorId);

// Rota: POST /post
// Descrição: Insere um novo post.
router.post('/post', postController.inseriPost);

// Rota: POST /comentar
// Descrição: Adiciona um comentário a um post.
router.post('/comentar', postController.commitPost);

// Rota: PUT /post/:id
// Descrição: Atualiza um post por ID.
router.put('/post/:id', postController.atualizaOpost);

// Rota: DELETE /cliente/:id
// Descrição: Exclui um post por ID.
router.delete('/cliente/:id', postController.excluiPost);

/* ====================== dados ======================= */


// Rota: POST /adicionar-dado
// Descrição: Adiciona um novo dado.
app.post('/adicionardado', objectController.adicionarDado);

// Rota: GET /buscar-dados-ativos
// Descrição: Busca um dado ativo.
app.get('/buscardado', objectController.buscarUmDado);

// Rota: GET /buscar-todos-dados
// Descrição: Busca todos os dados.
app.get('/buscartodosdados', objectController.buscarTodosDados);

// Rota: PUT /atualizar-dado/:id
// Descrição: Atualiza um dado específico pelo seu ID.
app.put('/atualizardado/:id', objectController.atualizarDado);

// Rota: DELETE /excluir-dado/:id
// Descrição: Exclui um dado específico pelo seu ID.
app.delete('/excluirdado/:id', objectController.excluirDado);


// Exporta o router para uso em outros arquivos
module.exports = router;