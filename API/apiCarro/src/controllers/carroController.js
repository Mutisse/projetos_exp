const caroServices = require('../services/carroServices') // importa a class carros services.

module.exports = {

    // funcao que vai levar todos os registros do banco de dados.
    buscarTodos: async (req, res) => {
        let json = { error: '', result: [] };

        let carros = await caroServices.buscarTodos();

        for (let i in carros) {
            json.result.push({
                codigo: carros[i].codigo,
                descricao: carros[i].modelo,
                matricula: carros[i].placa

            });
        }
        res.json(json);
    },


    // funcao que vai levar apenas um registros do banco de dados por pesquisa.
    buscarUm: async (req, res) => {
        let json = { error: '', result: {} }
        let codigo = req.params.codigo;
        let carro = await caroServices.buscarUm(codigo);
        if (carro) {
            json.result = carro;
        }
        res.json(json)
    },

    // funcao que vai inserir dados na base de  dodos
    inserir: async (req, res) => {
        let json = { error: '', result: {} }
        let modelo = req.body.modelo;
        let placa = req.body.placa;
        if (modelo && placa) {
            let carroCodigo = await caroServices.inserir(modelo, placa);
            json.result = {
                codigo: carroCodigo,
                modelo,
                placa
            }
        } else {
            json.error = 'campos nao enviados';
        }
        res.json(json)
    },

    // funcao que vai atualizar os dados na base de  dodos
    alterar: async (req, res) => {
        let json = { error: '', result: {} }
        let codigo = req.params.codigo;
        let modelo = req.body.modelo;
        let placa = req.body.placa;
        if (codigo && modelo && placa) {
            await caroServices.alterar(codigo, modelo, placa);
            json.result = {
                codigo,
                modelo,
                placa
            }
        } else {
            json.error = 'campos nao enviados';
        }
        res.json(json)
    },

    // funcao que vai excluir os dados na base de  dodos
    excluir: async (req,res) => {
        let json = {error:'', result:{}}
        await caroServices.excluir(req.params.codigo)
        res.json(json)
    }

}