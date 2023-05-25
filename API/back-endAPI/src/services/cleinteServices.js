const db = require('../dbConfig');  // importa a class que faz a conexao com o banco de dados
const { setDoc, doc, updateDoc, getDoc, collection, query, where, getDocs } = require('firebase/firestore');




/* codigo usuario
Esta função gera um número aleatório entre 1000 e 9999.*/
function generateCode() {
    const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const randomNumber = Math.floor(Math.random() * 9000);
    const randomLetter = letters[Math.floor(Math.random() * letters.length)];
    return randomLetter + "@" + randomNumber;
}


/* Esta função verifica se o código já existe na referência "codigos"
do seu banco de dados Firebase. Se o código já existir, a função retornará true.
Caso contrário, retornará false.*/
async function checkCodeExists(code) {
    const q = query(collection(db, "Usuarios"), where("idUser", "==", code));
    const querySnapshot = await getDocs(q);
    return !querySnapshot.empty;
}

/*
Essa função usa a função generateCode() para gerar um código aleatório e, em seguida,
usa a função checkCodeExists() para verificar se o código já existe. Se o código já
existir, a função gera um novo código. Se o código não existir, a função retorna
o código gerado.
*/

async function generateUniqueCode() {
    let code = generateCode();
    while (await checkCodeExists(code)) {
        code = generateCode();
    }
    return code;
}

module.exports = {

    /*Nessa  funcao, a consulta dos documentos é feita  usando a função query e a condição where para buscar os documentos com o status diferente de false. Os documentos encontrados são armazenados no array documents, que é retornado no final da função.*/
    buscaTodosDocumentos: async () => {
        try {
            const documents = [{}];
            const select = query(collection(db, "Usuarios"), where("status", "!=", false));
            const querySnapshot = await getDocs(select);

            if (querySnapshot.empty) {
                console.log("Não existem documentos na coleção 'Usuarios' com status diferente de false");
            } else {
                querySnapshot.forEach((doc) => {
                    documents.push({ id: doc.id, data: doc.data() });
                });

            }
            return documents;
        } catch (error) {
            console.error("Erro ao buscar documentos: ", error);
            throw error;
        }
    },

    /*Nesta função, utilizamos os parâmetros nome e sobrenome para filtrar a busca por nome e sobrenome específicos. Além disso, adicionamos uma cláusula where para garantir que o campo status seja diferente de false, mantendo apenas os documentos ativos.

Após executar a consulta, verificamos se o querySnapshot está vazio. Se estiver vazio, significa que nenhum documento correspondeu aos critérios de busca, e retornamos null. Caso contrário, percorremos o querySnapshot e adicionamos os documentos encontrados ao array documentos, que contém objetos com o ID e os dados do documento.

Por fim, retornamos o array documentos com os resultados da busca por nome e sobrenome.
 */
    buscaDocumentosPorId: async (id) => {
        try {
            const docRef = doc(db, "Usuarios", id);
            const docSnap = await getDoc(docRef)
            if (docSnap.exists()) {
                if (docSnap.data().status === false) {

                    return null;
                }

            }
            return docSnap.data();
        } catch (error) {
            console.error("Erro ao buscar documentos por nome e sobrenome: ", error);
            throw error;
        }
    },




    /*Nesta função, o parâmetro dados representa um objeto contendo os dados do documento que você deseja inserir.
Utilizamos a função addDoc para adicionar um novo documento à coleção "Usuarios". O objeto dados é spread ({...dados}) para garantir que todos os campos sejam copiados, e também adicionamos o campo status com o valor true, indicando que o documento está ativo.

Após a inserção bem-sucedida, o docRef conterá uma referência ao documento recém-criado. Neste exemplo, apenas exibimos o ID do documento no console e retornamos esse ID como resultado da função.

Caso ocorra algum erro durante a inserção, ele será capturado no bloco catch e exibido no console. Em seguida, a exceção é lançada novamente para tratamento em um nível superior, se necessário. */

    adicionaDocumento: async (documento) => {
        var codigo = await generateUniqueCode();
        try {
            const docRef = doc(db, "Usuarios", codigo.toString());
            await setDoc(docRef, { ...documento, status: true });
            console.log("Documento inserido com o ID: ", docRef.id);
            return docRef.id;
        } catch (error) {
            console.error("Erro ao inserir documento: ", error);
            throw error;
        }
    },


    /*Neste método, o parâmetro id representa o ID do documento que você deseja atualizar, e o parâmetro novosDados representa um objeto contendo os novos dados que você deseja atribuir ao documento.

Utilizamos a função doc para obter uma referência ao documento específico que queremos atualizar na coleção "Usuarios". Passamos o ID do documento e o nome da coleção como argumentos.

Em seguida, utilizamos a função updateDoc para atualizar os dados do documento com os novos dados fornecidos. Utilizamos o operador spread ({ ...novosDados }) para copiar todos os campos dos novos dados. Isso garantirá que apenas os campos fornecidos sejam atualizados, mantendo os demais campos inalterados.

Após a atualização bem-sucedida, exibimos a mensagem "Documento atualizado com sucesso" no console.

Se ocorrer algum erro durante a atualização, ele será capturado no bloco catch, exibido no console e lançado novamente como exceção para tratamento em um nível superior, se necessário.
*/
    atualizaDocumento: async (id, novosDados) => {
        try {
            const docRef = doc(db, "Usuarios", id);
            await updateDoc(docRef, { ...novosDados });
            console.log("Documento atualizado com sucesso");
            return true
        } catch (error) {
            console.error("Erro ao atualizar documento: ", error);
            throw error;
        }
    },

    /*Neste método, o parâmetro id representa o ID do documento que você deseja eliminar.

Utilizamos a função doc para obter uma referência ao documento específico que queremos eliminar na coleção "Usuarios". Passamos o ID do documento e o nome da coleção como argumentos.

Em seguida, utilizamos a função updateDoc para atualizar o campo status do documento para false, indicando que o documento está sendo "eliminado" logicamente. O restante dos dados no documento não é alterado.

Após a eliminação lógica bem-sucedida, exibimos a mensagem "Documento eliminado com sucesso" no console.

Se ocorrer algum erro durante a eliminação lógica, ele será capturado no bloco catch, exibido no console e lançado novamente como exceção para tratamento em um nível superior, se necessário.

*/
    excluiDocumento: async (id) => {
        try {
            const docRef = doc(db, "Usuarios", id);
            await updateDoc(docRef, { status: false });
            console.log("Documento eliminado com sucesso");
            return true
        } catch (error) {
            console.error("Erro ao eliminar documento: ", error);
            throw error;
        }
    },

    /*Agora, quando você chamar pesquisarUsuarioPorNomeSobrenome(nome, sobrenome), a função retornará uma Promise que será resolvida com um array contendo os IDs dos usuários que correspondem ao filtro de pesquisa em tempo real. A função continuará ouvindo as alterações na coleção de usuários e atualizando os resultados conforme o usuário digita. Você também pode chamar unsubscribe() para parar de ouvir as alterações quando necessário. */

    pesquisarUsuarioPorNomeSobrenome: (nome, sobrenome) => {
        return new Promise((resolve, reject) => {
            const query = nome.toLowerCase() + sobrenome.toLowerCase();
            const usuariosRef = collection(db, "Usuarios");

            const unsubscribe = onSnapshot(usuariosRef, (snapshot) => {
                const usuariosEncontrados = snapshot.docs
                    .filter((doc) => {
                        const { pnome, sobrenome } = doc.data();
                        const nomeCompleto = pnome.toLowerCase() + sobrenome.toLowerCase();
                        return nomeCompleto.includes(query);
                    })
                    .map((doc) => doc.id);

                resolve(usuariosEncontrados);
            }, (error) => {
                console.error("Erro ao pesquisar usuários por nome e sobrenome: ", error);
                reject(error);
            });

            return unsubscribe;
        });
    }
}