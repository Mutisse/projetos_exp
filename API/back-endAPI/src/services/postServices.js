const db = require('../dbConfig');  // importa a class que faz a conexao com o banco de dados
const { setDoc, doc, updateDoc, getDoc, collection, query, where, getDocs } = require('firebase/firestore');



function generateCode() {
  const letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  const randomNumber = Math.floor(Math.random() * 900000);
  const randomLetter = letters[Math.floor(Math.random() * letters.length)];
  return randomLetter + "" + randomNumber;
}

/* Esta função verifica se o código já existe na referência "codigos"
do seu banco de dados Firebase. Se o código já existir, a função retornará true.
Caso contrário, retornará false.*/
async function checkCodeExists(code) {
  const q = query(collection(db, "postagens"), where("idUser", "==", code));
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

  buscarDocumentosPostagens: async () => {
    try {
      const documentos = []; // Array para armazenar os documentos encontrados
      const select = query(collection(db, "postagens"), where("status", "!=", false)); // Query para buscar documentos na coleção "postagens"
      const querySnapshot = await getDocs(select); // Executa a consulta

      if (querySnapshot.empty) {
        console.log("Não existem documentos na coleção 'postagens' com status diferente de false");
      } else {
        querySnapshot.forEach((doc) => {
          documentos.push({ id: doc.id, data: doc.data() }); // Adiciona cada documento encontrado ao array
        });
      }

      return documentos;
    } catch (error) {
      console.error("Erro ao buscar documentos: ", error);
      throw error;
    }
  },
  buscaDocumentosPorId: async (id) => {
    try {
      const docRef = doc(db, "foundItems", id);
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

  criarDocumento: async (novoDocumento) => {
    try {
      const codigo = await generateUniqueCode();
      const docRef = doc(db, "foundItems", codigo.toString());
      await setDoc(docRef, { ...novoDocumento, status: true });
      console.log("Documento inserido com o ID:", docRef.id);
      return docRef.id;
    } catch (error) {
      console.error("Erro ao inserir documento: ", error);
      throw error;
    }
  },
  adicionaCommit: async (idDocumento, dadosAtualizados) => {
    try {
      const code = await generateUniqueCode();
      const docRef = doc(db, "foundItems", idDocumento, "commitPost", code.toString());
      await setDoc(docRef, dadosAtualizados);
      console.log("Documento comentado com sucesso!");
      return docRef.id;
    } catch (error) {
      console.error("Erro ao responder documento: ", error);
    }
  }
  ,
  atualizarDocumento: async (idDocumento, dadosAtualizados) => {
    try {
      const docRef = doc(db, "foundItems", idDocumento);
      await updateDoc(docRef, { ...dadosAtualizados });
      console.log("Documento atualizado com sucesso!");
      return docRef.id;
    } catch (error) {
      console.error("Erro ao atualizar documento: ", error);
      throw error;
    }
  },

  excluiDocumento: async (id) => {
    try {
      const docRef = doc(db, "foundItems", id);
      await updateDoc(docRef, { status: false });
      console.log("Documento eliminado com sucesso");
      return docRef.id;
    } catch (error) {
      console.error("Erro ao eliminar documento: ", error);
      throw error;
    }
  }



}