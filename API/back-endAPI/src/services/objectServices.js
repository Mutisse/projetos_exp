const fs = require('fs');


module.exports = {
    verificarECriarArquivoTxt(caminhoArquivo, informacao) {
        try {
            fs.accessSync(caminhoArquivo, fs.constants.F_OK);
            // O arquivo já existe, portanto não precisa ser criado novamente
            console.log('O arquivo já existe.');
            adicionarInformacaoAoArquivo(caminhoArquivo, informacao);
            recuperarInformacaoDoArquivo(caminhoArquivo);
        } catch (error) {
            // O arquivo não existe, então cria um novo e adiciona a informação
            fs.writeFileSync(caminhoArquivo, informacao);
            console.log('Arquivo criado com sucesso.');
        }
    },

    adicionarInformacaoAoArquivo: async (caminhoArquivo, informacao) => {
        try {
            fs.appendFileSync(caminhoArquivo, informacao);
            console.log('Informação adicionada ao arquivo.');
        } catch (error) {
            console.error("Erro ao adicionar informação ao arquivo: ", error);
        }
    },
    recuperarInformacaoDoArquivoPorId: async (caminhoArquivo, id) => {
        const informacao = fs.readFileSync(caminhoArquivo, 'utf8');
        const dados = JSON.parse(informacao);
        const informacaoEncontrada = dados.find(item => item.id === id && item.status !== false);
        return informacaoEncontrada;
    },

    buscarInformacaoNoArquivo: async (caminhoArquivo, informacaoBuscada) => {
        const informacao = fs.readFileSync(caminhoArquivo, 'utf8');
        const dados = JSON.parse(informacao);
        const informacoesEncontradas = dados.filter(item => item.status !== false && item.descricao.includes(informacaoBuscada));
        if (informacoesEncontradas.length > 0) {
            console.log('As informações foram encontradas no arquivo:', informacoesEncontradas);
        } else {
            console.log('As informações não foram encontradas no arquivo.');
        }
    },

    apagarInformacaoNoArquivo: async (caminhoArquivo, idParaApagar) => {
        const informacao = fs.readFileSync(caminhoArquivo, 'utf8');
        const dados = JSON.parse(informacao);

        const indice = dados.findIndex(item => item.id === idParaApagar);
        if (indice !== -1) {
            dados[indice].status = false;
            const informacaoAtualizada = JSON.stringify(dados);
            fs.writeFileSync(caminhoArquivo, informacaoAtualizada);
            console.log('Informação removida do arquivo.');
        } else {
            console.log('ID não encontrado no arquivo.');
        }
    },

    atualizarInformacaoNoArquivo: async (caminhoArquivo, id, novoStatus) => {
        const informacao = fs.readFileSync(caminhoArquivo, 'utf8');
        const dados = JSON.parse(informacao);

        const indice = dados.findIndex(item => item.id === id);
        if (indice !== -1) {
            dados[indice].status = novoStatus;
            const informacaoAtualizada = JSON.stringify(dados);
            fs.writeFileSync(caminhoArquivo, informacaoAtualizada);
            console.log('Informação atualizada no arquivo.');
        } else {
            console.log('ID não encontrado no arquivo.');
        }
    }


}