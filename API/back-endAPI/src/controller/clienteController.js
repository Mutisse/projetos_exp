const clienteServices = require('../services/cleinteServices') // importa a class 




module.exports = {
    /*utilizamos o método map para percorrer o array clientes e transformá-lo em um novo array com a estrutura desejada. utilizamos a sintaxe cliente => { ... } no map para iterar sobre os elementos do array.*/
    buscaTodosClientes: async (req, res) => {
        try {
            const clientes = await clienteServices.buscaTodosDocumentos();
            const json = {
                result: clientes.map(cliente => ({
                    id: cliente.codigo,
                    nome: cliente.pnome,
                    unome: cliente.sobrenome,
                    sexo: cliente.gernero,
                    e_mail: cliente.email,
                    data_de_nascimento: cliente.dateNasc,
                    password: cliente.senha,
                    contacto: cliente.telefone,
                    foto_De_Perfil: cliente.fotoDePerfil
                })),
                error: ''
            };
            res.json(clientes);
        } catch (error) {
            console.error("Erro ao buscar clientes: ", error);
            res.status(500).json({ error: "Erro ao buscar clientes" });
        }
    },
    /*Nesta função, usou se o req.params.nome e req.params.sobrenome para obter os parâmetros de pesquisa passados na URL. Em seguida, chamamos a função buscarClientePorNomeSobrenome do serviço clienteServices, que deve ser implementada para buscar o cliente no banco de dados com base nos critérios de pesquisa.

Se o cliente for encontrado, retornamos um objeto json com o cliente encontrado e uma propriedade error vazia. Caso contrário, retornamos uma resposta com status 404 e um objeto json indicando que o cliente não foi encontrado.

*/
    buscaUmCliente: async (req, res) => {
        try {
            /* const nome = req.params.nome;
             const sobrenome = req.params.unome;*/

            const id = req.params.id;

            const cliente = await clienteServices.buscaDocumentosPorId(id);

            if (cliente) {
                const json = {
                    result: {
                        id: cliente.codigo,
                        nome: cliente.pnome,
                        unome: cliente.sobrenome,
                        sexo: cliente.gernero,
                        e_mail: cliente.email,
                        data_de_nascimento: cliente.dateNasc,
                        password: cliente.senha,
                        contacto: cliente.telefone,
                        foto_De_Perfil: cliente.fotoDePerfil
                    },
                    error: ''
                };
                res.json(cliente);
            } else {
                res.send(`Não foi encontrado nenhum documento na coleção 'Usuarios' com o nome '`);
            }
        } catch (error) {
            console.error("Erro ao buscar cliente por nome e sobrenome: ", error);
            res.status(500).json({ error: "Erro ao buscar cliente por nome e sobrenome" });
        }
    },
    /*Nesta função, usou se req.body para obter os dados do novo cliente que devem ser enviados no corpo da solicitação. Em seguida, chamamos a função inserirCliente do serviço clienteServices, que deve ser implementada para inserir o novo cliente no banco de dados.
Se o cliente for inserido com sucesso, retornamos um objeto json com o cliente inserido e uma propriedade error vazia. Caso ocorra algum erro durante a inserção, retornamos uma resposta com status 500 e um objeto json indicando que ocorreu um erro ao inserir o cliente.*/
    inseriCliente: async (req, res) => {
        try {
            const novoCliente = req.body;
            const clienteInserido = await clienteServices.adicionaDocumento(novoCliente);

            const addDoc = {
                result: {
                    nome: clienteInserido.pnome,
                    unome: clienteInserido.sobrenome,
                    e_mail: clienteInserido.email,
                    password: clienteInserido.senha,
                    contacto: clienteInserido.telefone,
                },
                error: ''
            };
            res.json(addDoc);
            console.log("Documento inserido com o ID: ", addDoc.id);
        } catch (error) {
            console.error("Erro ao inserir cliente: ", error);
            res.status(500).json({ error: "Erro ao inserir cliente" });
        }
    },

    /*Nesta função, usou se req.params.id para obter o ID do cliente a ser atualizado dos parâmetros da solicitação e req.body para obter os novos dados do cliente a serem atualizados. Em seguida, chamamos a função alterarCliente do serviço clienteServices, que deve ser implementada para atualizar o cliente no banco de dados.

Se o cliente for atualizado com sucesso, retornamos um objeto json com o cliente atualizado e uma propriedade error vazia. Se o cliente não for encontrado, retornamos uma resposta com status 404 e um objeto json indicando que o cliente não foi encontrado. Caso ocorra algum erro durante a atualização, retornamos uma resposta com status 500 e um objeto json indicando que ocorreu um erro ao alterar o cliente.
*/
    atulizaDadosDoCliente: async (req, res) => {
        try {
            const clienteId = req.params.id;
            const novosDados = req.body;
            const clienteAtualizado = await clienteServices.atualizaDocumento(clienteId, novosDados);

            if (!clienteAtualizado) {
                return res.status(404).json({ error: "Cliente não encontrado" });
            }

            const updatDoc = {
                result: {
                    id: clienteAtualizado.codigo,
                    nome: clienteAtualizado.pnome,
                    unome: clienteAtualizado.sobrenome,
                    sexo: clienteAtualizado.gernero,
                    e_mail: clienteAtualizado.email,
                    data_de_nascimento: clienteAtualizado.dateNasc,
                    password: clienteAtualizado.senha,
                    contacto: clienteAtualizado.telefone,
                    foto_De_Perfil: clienteAtualizado.fotoDePerfil
                },
                error: ''
            };
            res.json(updatDoc);
            res.send("Documento atualizado com sucesso");
        } catch (error) {
            console.error("Erro ao alterar cliente: ", error);
            res.status(500).json({ error: "Erro ao alterar cliente" });
        }
    },
    /*Neste método, utilizamos req.params.id para obter o ID do cliente a ser excluído dos parâmetros da requisição. Em seguida, chamamos a função excluirCliente do serviço clienteServices, que deve ser implementada para realizar a exclusão lógica do cliente, atualizando o campo status para false no banco de dados.

Se o cliente for excluído com sucesso, retornamos um objeto json com a mensagem "Cliente excluído com sucesso" e uma propriedade error vazia. Se o cliente não for encontrado, retornamos uma resposta com status 404 e um objeto json indicando que o cliente não foi encontrado. Caso ocorra algum erro durante a exclusão, retornamos uma resposta com status 500 e um objeto json indicando que ocorreu um erro ao excluir o cliente.*/
    excluiCliente: async (req, res) => {
        try {
            const clienteId = req.params.id;
            console.log(clienteId)
            const clienteExcluido = await clienteServices.excluiDocumento(clienteId);

            if (!clienteExcluido) {
                return res.status(404).json({ error: "Cliente não encontrado" });
            }

            const excluir = {
                result: "Cliente excluído com sucesso",
                error: ""
            };
            res.json(excluir);
        } catch (error) {
            console.error("Erro ao excluir cliente: ", error);
            res.status(500).json({ error: "Erro ao excluir cliente" });
        }
    }


}