const objectServices = require('../services/ObjectServices') // importa a class 
const fs = require('fs');

let lastCategoryId = 0;
let lastTypeId = 0;

function generateUniqueCategoryId(filePath) {
    lastCategoryId++;
    const categoryId = lastCategoryId;

    while (checkIfIdExists(filePath, categoryId)) {
        lastCategoryId++;
        categoryId = lastCategoryId;
    }

    return categoryId;
}

function generateUniqueTypeId(filePath) {
    lastTypeId++;
    const typeId = lastTypeId;

    while (checkIfIdExists(filePath, typeId)) {
        lastTypeId++;
        typeId = lastTypeId;
    }

    return typeId;
}

function checkIfIdExists(filePath, id) {
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        const ids = data.split('\n');
        return ids.includes(id.toString());
    } catch (error) {
        console.error("Erro ao ler o arquivo: ", error);
        return false;
    }
}

module.exports = {

    adicionarDado: async (req, res) => {
        try {
            const objetos = await objectServices.adicionarInformacaoAoArquivo();
            const caminho = 'arquivo.txt';
            const informacoes = [];

            objetos.forEach(object => {

                const item = {
                    categoria: {
                        id: generateUniqueCategoryId(),
                        descricao: object.nome,
                        status: true
                    },
                    tipobjct: {
                        id: generateUniqueTypeId(),
                        descricao: object.nome,
                        status: true
                    },
                    ...object
                };

                informacoes.push(JSON.stringify(item) + '\n');
            });

            informacoes.forEach(async informacao => {
                await verificarECriarArquivoTxt(caminho, informacao);
            });

            res.json(informacoes);
        } catch (error) {
            console.error("Erro ao buscar objeto: ", error);
            res.status(500).json({ error: "Erro ao buscar objeto" });
        }
    },

    buscarUmDado: async (req, res) => {
        try {
            const dado = await objectServices.buscarUmDado();

            if (dado && dado.status !== false) {
                res.json(dado);
            } else {
                res.status(404).json({ error: "Dado não encontrado" });
            }
        } catch (error) {
            console.error("Erro ao buscar dado: ", error);
            res.status(500).json({ error: "Erro ao buscar dado" });
        }
    },


    buscarTodosDados: async (req, res) => {
        try {
            const dados = await objectServices.buscarTodosDados();
            const dadosFiltrados = dados.filter(dado => dado.status !== false);
            res.json(dadosFiltrados);
        } catch (error) {
            console.error("Erro ao buscar dados: ", error);
            res.status(500).json({ error: "Erro ao buscar dados" });
        }
    },

    atualizarDado: async (req, res) => {
        const id = req.params.id;
        const novosDados = req.body;

        try {
            const dadoExistente = await objectServices.recuperarInformacaoDoArquivoPorId(id);
            if (dadoExistente) {
                const dadosAtualizados = { ...dadoExistente, ...novosDados };
                await objectServices.atualizarInformacaoNoArquivo(id, dadosAtualizados);
                res.json(dadosAtualizados);
            } else {
                res.status(404).json({ error: "Dado não encontrado" });
            }
        } catch (error) {
            console.error("Erro ao atualizar dado: ", error);
            res.status(500).json({ error: "Erro ao atualizar dado" });
        }
    },

    excluirDado: async (req, res) => {
        const id = req.params.id;
        try {
            const dadoExistente = await objectServices.recuperarInformacaoDoArquivoPorId(id);
            if (dadoExistente) {
                const dadosAtualizados = { ...dadoExistente, status: false };
                await objectServices.apagarInformacaoNoArquivo(id, dadosAtualizados);
                res.json(dadosAtualizados);
            } else {
                res.status(404).json({ error: "Dado não encontrado" });
            }
        } catch (error) {
            console.error("Erro ao excluir dado: ", error);
            res.status(500).json({ error: "Erro ao excluir dado" });
        }
    }
};



/*
adicionarDado: Este método recebe uma requisição (req) e uma resposta (res). Ele busca todos os documentos existentes, gera códigos únicos de categoria e tipo, cria um objeto itens com base nos documentos encontrados e outras informações, converte esse objeto para uma string JSON e a adiciona em um arquivo de texto usando a função verificarECriarArquivoTxt. Em seguida, ele envia a resposta com o objeto itens em formato JSON.

buscarUmDado: Este método recebe uma requisição (req) e uma resposta (res). Ele busca todos os documentos existentes e filtra os documentos com status diferente de false. Em seguida, envia a resposta com os documentos filtrados em formato JSON.

buscarTodosDados: Este método é semelhante ao buscarUmDado, pois também recebe uma requisição (req) e uma resposta (res). Ele busca todos os documentos existentes e filtra os documentos com status diferente de false. Em seguida, envia a resposta com os documentos filtrados em formato JSON.

atualizarDado: Este método recebe uma requisição (req) e uma resposta (res). Ele extrai o ID e os novos dados da requisição e verifica se o dado com o ID fornecido existe. Se existir, ele mescla os dados existentes com os novos dados e chama o método atualizarDocumento do objectServices para atualizar o documento no banco de dados. Em seguida, envia a resposta com os dados atualizados em formato JSON. Se o dado não for encontrado, retorna uma resposta com o status 404 e uma mensagem de erro.

excluirDado: Este método é semelhante ao atualizarDado, pois também recebe uma requisição (req) e uma resposta (res). Ele extrai o ID da requisição e verifica se o dado com o ID fornecido existe. Se existir, ele define o status do dado como false e chama o método atualizarDocumento do objectServices para atualizar o documento no banco de dados. Em seguida, envia a resposta com os dados atualizados em formato JSON. Se o dado não for encontrado, retorna uma resposta com o status 404 e uma mensagem de erro.
*/ 