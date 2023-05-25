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

// modelo da colecao
const objecModel = {
id:1,
categoria:"doocumentos",
tipobjct:["Bi","cedula","etc"]
}


// Função para adicionar um novo documento à coleção
async function adicionarDocumento(novoDocumento) {
  try {
    const docRef = await doc(db, "Usuarios", generateCode().toString());
    setDoc(docRef, objecModel);
    //await firebase.add(novoDocumento);
    console.log("Documento adicionado com sucesso!");
    //alert("Documento adicionado com sucesso!")
  } catch (error) {
    console.error("Erro ao adicionar documento: ", error);
  }
}

console.log(adicionarDocumento(objecModel))



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

//buscarDocumentosPorId("6607")

async function buscarTodos() {
  const querySnapshot = await getDocs(collection(db, "Usuarios"));
  querySnapshot.forEach((doc) => {
    // doc.data() is never undefined for query doc snapshots
    console.log(doc.id, " => ", doc.data());
  })
}

//buscarTodos()
async function buscarDocumentos() {
  try {

    const q = query(collection(db, "Usuarios"), where("status", "==", true));
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


// buscarDocumentos();

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
  pnome: "Mutisse"
}

// atualizarDocumento("2484", ModelActual)

//
// // Função para excluir um documento existente
async function excluirDocumento(idDocumento) {
  try {
    const washingtonRef = doc(db, "Usuarios", idDocumento);

    // Set the "capital" field of the city 'DC'
    await updateDoc(washingtonRef, {
      status: false
    });
    console.log("Documento excluído com sucesso!");
  } catch (error) {
    console.error("Erro ao excluir documento: ", error);
  }
}
// excluirDocumento("6607")
