const { post } = require('../routes');
const postServices = require('../services/postServices') // importa a class


function dataHorafunction() {
    // Array com os nomes dos dias da semana
    const diasDaSemana = ["Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"];

    // Array com os nomes dos meses
    const meses = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

    // Obtém a data atual
    const data = new Date();

    // Obtém o dia da semana (valor numérico de 0 a 6, onde 0 é Domingo e 6 é Sábado)
    const diaSemana = data.getDay();

    // Obtém o dia do mês
    const dia = data.getDate();

    // Obtém o mês (valor numérico de 0 a 11, onde 0 é Janeiro e 11 é Dezembro)
    const mes = data.getMonth();

    // Obtém o ano
    const ano = data.getFullYear();

    // Obtém as horas
    const horas = data.getHours();

    // Obtém os minutos
    const minutos = data.getMinutes();

    // Obtém os segundos
    const segundos = data.getSeconds();

    // Retorna a data formatada
    return `${diasDaSemana[diaSemana]}: ${dia} de ${meses[mes]} de ${ano} ${horas}:${minutos}:${segundos}`;
}


module.exports = {

    /*A função buscarTodosPost irá retornar um array de objetos contendo os atributos itemId, userId, description, location, dateLost e contactInfo para cada post encontrado na busca.
    
    Certifique-se de ter os campos corretamente definidos nos objetos retornados pela função clienteServices.buscarTodosDocumentos() ou faça os ajustes necessários para refletir a estrutura correta dos objetos retornados. */
    buscaTodosPosts: async (req, res) => {
        try {
          const posts = await postServices.buscarDocumentosPostagens();
          const postagens = {
            result: posts.map(post => ({
              itemId: post.itemId,
              userId: post.userId,
              description: post.descriptionItem,
              location: post.locationLost,
              dateLost: post.dateLost,
              datePost: post.datePost,
              contactInfo: post.contactInfo
            })),
            error: ''
          };
      
          res.json(posts); 
        } catch (error) {
          console.error("Erro ao buscar clientes: ", error);
          res.status(500).json({ error: "Erro ao buscar clientes" });
        }
      },
      

    /* assumindo que você esteja usando uma rota como /post/:id para buscar o post por ID, o parâmetro id é capturado de req.params. Em seguida, o método clienteServices.buscarDocumentoPorId(id) é chamado para obter o post correspondente ao ID fornecido. 
     Se o post não for encontrado, a resposta terá o status 404 e um JSON com a mensagem de erro adequada. Caso contrário, os dados do post são mapeados para o formato desejado e enviados na resposta. */

    buscaPostPorId: async (req, res) => {
        try {
            const { id } = req.params;
            const post = await clienteServices.buscarDocumentoPorId(id);
            if (!post) {
                return res.status(404).json({ error: "Cliente não encontrado" });
            }
            const postagens = {
                result: {
                    itemId: post.itemId,
                    userId: post.userId,
                    description: post.description,
                    location: post.location,
                    dateLost: post.dateLost,
                    contactInfo: post.contactInfo
                },
                error: ''
            };
            res.json(post);
        } catch (error) {
            console.error("Erro ao buscar post: ", error);
            res.status(500).json({ error: "Erro ao buscar post" });
        }
    },


    atualizaOpost: async (req, res) => {
        const id = req.params;
        try {
            // Verifica se o indivíduo existe
            const postUpdate = await individuoServices.buscarIndividuoPorId(id);
            if (!postUpdate) {
                return res.status(404).json({ error: "post não encontrado" });
            }

            // Atualiza os atributos do indivíduo
            postUpdate.itemId = itemId;
            postUpdate.userId = userId;
            postUpdate.description = description;
            postUpdate.location = location;
            postUpdate.dateLost = dateLost;
            postUpdate.datePost = dataHorafunction()
            postUpdate.contactInfo = contactInfo;

            // Salva as alterações no banco de dados
            await individuoServices.atualizarIndividuo(id, postUpdate);

            // Retorna a resposta de sucesso
            return res.json({ message: `post atualizado com sucesso ${ inserirPost.codigo}` });
        } catch (error) {
            console.error("Erro ao atualizar post: ", error);
            return res.status(500).json({ error: "Erro ao atualizar posta" });
        }
    },
    inseriPost: async (req, res) => {
        try {
            const novoPost = req.body;
            const inserirPost = await clienteServices.adicionarDocumento(novoPost);

            const addDoc = {
                result: {
                    itemId: itemId,
                    userId: userId,
                    description: description,
                    location: location,
                    dateLost: dateLost,
                    datePost: dataHorafunction(),
                    contactInfo: contactInfo
                },
                error: ''
            };

            res.json(addDoc);
           res.send(`Documento inserido com o ID: ", ${inserirPost.codigo}`);
        } catch (error) {
            console.error("Erro ao inserir post: ", error);
            res.status(500).json({ error: "Erro ao inserir post" });
        }
    },
    excluiPost: async (req, res) => {
        try {
            const postId = req.params.id;
            const excluirPost = await clienteServices.excluirDocumento(postId);

            if (!excluirPost) {
                return res.status(404).json({ error: "post não encontrado" });
            }

            const excluir = {
                result: "post excluído com sucesso",
                error: ""
            };
            res.json(excluir);
        } catch (error) {
            console.error("Erro ao excluir post: ", error);
            res.status(500).json({ error: "Erro ao excluir post" });
        }
    },

    commitPost: async (req, res) => {
        try {
            const Postcommit = req.body;
            const inserirPost = await clienteServices.adicionaCommit(Postcommit);

            const addDoc = {
                result: {
                    itemId: Postcommit.itemId,
                    userId: Postcommit.userId,
                    locationFonud: location,
                    text: Postcommit.text,
                    commentId: inserirPost.codigo, // Adiciona o ID do comentário
                    datePost: dataHorafunction(),
                    contactInfo: Postcommit.contactInfo
                },
                error: ''
            };

            res.json(addDoc);
            res.send(`Documento inserido com o ID: ${ inserirPost.codigo}`);
        } catch (error) {
            console.error("Erro ao inserir post: ", error);
            res.status(500).json({ error: "Erro ao inserir post" });
        }
    },

}