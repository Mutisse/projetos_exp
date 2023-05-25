const { excluir } = require('../controllers/carroController');
const db = require('../db');  // importa a class que faz a conexao com o banco de dados 

module.exports = {
    // funcao que faz o a requisica de todos  dados na tabela btcaros 
    buscarTodos: () => {
        return new Promise((aceito, rejeitado) => {

            db.query('SELECT * FROM btcarros  where status != 0 ORDER by  modelo;', (error, results) => {
                if (error) { rejeitado(error); return; }
                aceito(results);
            });
        });

    },

    // funcao de pesquisa por id  
    buscarUm: (codigo) => {
        return new Promise((aceito, rejeitado) => {
            db.query('SELECT * FROM btcarros WHERE status != 0 and codigo=?', [codigo], (error, results) => {
                if (error) { rejeitado(error); return; }
                if (results.length > 0) {
                    aceito(results[0]);
                } else {
                    aceito(false);
                }

            });
        });

    },

    // funcao que vai inserir  dados
    inserir: (modelo, placa) => {
        return new Promise((aceito, rejeitado) => {
            db.query('INSERT INTO btcarros(modelo, placa) VALUES (?,?)', [modelo, placa], (error, results) => {
                if (error) { rejeitado(error); return; }
                aceito(results.inserirCodigo)

            });
        });

    },

    // funcao que vai atualizar dados 
    alterar: (codigo, modelo, placa) => {
        return new Promise((aceito, rejeitado) => {
            db.query('UPDATE btcarros SET modelo=?,placa=? WHERE codigo=?', [modelo, placa, codigo], (error, results) => {
                if (error) { rejeitado(error); return; }
                aceito(results)
            });
        });

    },
    // funcao que vai aliminar os  dados 
    excluir:(codigo) => {
        return new Promise((aceito, rejeitado) => {
            db.query('UPDATE btcarros SET status=0 WHERE codigo=?',[codigo], (error, results) => {
                if (error) { rejeitado(error); return; }
                aceito(results);
            });
        });

    },
};