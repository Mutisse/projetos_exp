// Import the functions you need from the SDKs you need
import { getDatabase, ref, set } from "firebase/database";
import { getFirestore, doc, setDoc, updateDoc, getDoc, collection, query, where, getDocs } from "firebase/firestore"
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBrmn8ny89UZtmOEN1fEj1QHxE5aB-oA18",
  authDomain: "itens-perdidos.firebaseapp.com",
  projectId: "itens-perdidos",
  storageBucket: "itens-perdidos.appspot.com",
  messagingSenderId: "802987114080",
  appId: "1:802987114080:web:4ccec22fc31eb69724cd05",
  measurementId: "G-1M888NRELC",
  databaseURL: "https://itens-perdidos-default-rtdb.firebaseio.com/"
}; //fim

const app = initializeApp(
  firebaseConfig
);

// Initialize Firebase
const db = getFirestore()




/* codigo usuario
Esta função gera um número aleatório entre 1000 e 9999.*/
function generateCode() { return Math.floor(Math.random() * 9000) + 1000; }


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

// modelo para criacao de usuarios.
const ModelUser = {
  pnome: "marta",
  sobrenome: "manuel",
  gernero: "f",
  email: "marta@uem.ac.com",
  dateNasc: "16 de marco. de 2000",
  senha: "3336",
  telefone: "8644283645",
  status: 'true',
  fotoDePerfil: ""
}


// Função para adicionar um novo documento à coleção
async function adicionarDocumento(novoDocumento) {
  try {
    var code = await generateUniqueCode();
    const docRef = await doc(db, "Usuarios", code.toString());
    setDoc(docRef, ModelUser);
    //await firebase.add(novoDocumento);
    console.log("Documento adicionado com sucesso!");
    //alert("Documento adicionado com sucesso!")
  } catch (error) {
    console.error("Erro ao adicionar documento: ", error);
  }
}

//adicionarDocumento(ModelUser)



// Função para buscar por id os documentos da coleção
async function buscarDocumentosPorId(id) {
  try {
    const docRef = doc(db, "Usuarios", id);
    const docSnap = await getDoc(docRef);
    if (docSnap.exists()) {
      console.log("Document data:", docSnap.data());
    } else {
      // docSnap.data() will be undefined in this case
      console.log("Nenhum documento! encontrado");
    }

  } catch (error) {
    console.error("Erro ao buscar documentos: ", error);
  }
}

//buscarDocumentosPorId("2484")

async function buscarDocumentos() {
  try {
    const q = query(collection(db, "Usuarios"), where("status", "!=", false));
    const querySnapshot = await getDocs(q);
    if (querySnapshot.empty) {
      console.log("Não existe documentos")
    } else {
      querySnapshot.forEach((doc) => {
        // doc.data() is never undefined for query doc snapshots
        console.log(doc.id, " => ", doc.data());
      });
    }

  } catch (error) {
    console.error("Erro ao buscar documentos: ", error);
  }
}


//buscarDocumentos();

// Função para atualizar um documento existente
async function atualizarDocumento(idDocumento, dadosAtualizados) {
  try {
    const washingtonRef = doc(db, "Usuarios", idDocumento);

    // Set the "capital" field of the city 'DC'
    await updateDoc(washingtonRef, dadosAtualizados);
    console.log("Documento atualizado com sucesso!");
  } catch (error) {
    console.error("Erro ao atualizar documento: ", error);
  }
}
const ModelActual = {
  // pnome: "Edilson"
  gernero: "F"
}

//atualizarDocumento("5574", ModelActual)


// Função para excluir um documento existente
async function excluirDocumento(idDocumento) {
  try {
    const status = doc(db, "Usuarios", idDocumento);

    //     // Set the "capital" field of the city 'DC'
    await updateDoc(status, {
      status: false
    });
    console.log("Documento excluído com sucesso!");
  } catch (error) {
    console.error("Erro ao excluir documento: ", error);
  }
}
 //excluirDocumento("6607")




// async function buscarTodos() {
//   const querySnapshot = await getDocs(collection(db, "Usuarios"));
//   querySnapshot.forEach((doc) => {
//     // doc.data() is never undefined for query doc snapshots
//     console.log(doc.id, " => ", doc.data());
//   })
// }
//buscarTodos()