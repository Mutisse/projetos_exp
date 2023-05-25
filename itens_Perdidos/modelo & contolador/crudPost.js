// Import the functions you need from the SDKs you need
import { getFirestore, doc, setDoc, updateDoc, getDoc, collection, query, where, getDocs } from "firebase/firestore"
import { initializeApp } from "firebase/app";


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




// essa funcao vai vai formar a data apartie do sistema
function dataHorafunction() {
  const meses = new Array("Janeiro", "Fevereiro", "Marco", "Abril", " Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro")
  const diasDaSemana = new Array("Domingoo", "Segunda-Feira", "Terca-Feira", "Quarto-Feira", "Quinta-Feira", "Sexta-Feira", "Sabado")
  const data = new Date()
  const semana = data.getDay()
  const dia = data.getDate()
  const mes = data.getMonth()
  const ano = data.getFullYear()
  const horas = data.getHours()
  const minutos = data.getMinutes()
  const segundos = data.getSeconds()

  return (diasDaSemana[semana] + ": " + dia + "/" + meses[mes] + "/" + ano + "  " + horas + ":" + minutos + ":" + segundos)
}

/* codigo usuario
Esta função gera um número aleatório entre 1000 e 9999.*/
function generateCode() { return "e-" + Math.floor(Math.random() * 9000) + 1000; }

//console.log(" codigo gerado "+generateCode())
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
  let code = await generateCode();
  while (await checkCodeExists(code)) {
    code = generateCode();
  }
  return code;
}


// modelo cria uma colecao de postes
const modelPost = {
  autor: "joao",
  Post: "Quando acontecia algum erro ou precisava mudar alguma coisa era um martírio, eu não entendia nada, não sabia onde as coisas estavam e como funcionavam. Resultado: foram muitas guerras em fóruns, Google e etc, até começar a entender alguma coisa, mas o que foi isto? Pressa e afobação em fazer as coisas e não ter procurado alguém que de fato que me apresentasse uma outra opção.",
  dataPost: dataHorafunction(),
  status: "true",
}



// Função para adicionar um novo documento à coleção
async function criarDocumento(novoDocumento) {

  try {
    const code = await generateUniqueCode();
    console.log(code)
    const docRef = await doc(db, "postagens", code.toString());
    setDoc(docRef, modelPost);
    //await firebase.add(novoDocumento);
    console.log("Documento adicionado com sucesso!");
    //alert("Documento adicionado com sucesso!")
  } catch (error) {
    console.error("Erro ao adicionar documento: ", error);
  }
}

//console.log(criarDocumento(modelPost))


// Função para buscar por id os documentos da coleção
async function buscarDocumentosPorId(id) {
  try {

    const docRef = doc(db, "postagens", id);
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

//buscarDocumentosPorId("E-5091000")


//funcao  que vai levar todos posts exitentes

async function buscarDocumentos() {
  try {

    const q = query(collection(db, "postagens"), where("status", "!=", false));
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


buscarDocumentos();


// modelo de atualizar
const ModelActual = {
  Post: "E se em um universo paralelo ícones da humanidade, responsáveis por feitos que são verdadeiros marcos, mudassem suas mentes e simplificassem suas ações seguindo instituições seculares? Martin Luther King Jr., o pastor e ativista pelos direitos civis nos Estados Unidos, que liderou a luta contra a segregação racial e proferiu",
  dataPost: dataHorafunction()
}
// Função para atualizar um documento existente
async function atualizarDocumento(idDocumento, dadosAtualizados) {
  try {
    const novoPost = doc(db, "postagens", idDocumento);

    // Set the "capital" field of the city 'DC'
    await updateDoc(novoPost, dadosAtualizados);
    console.log("Documento atualizado com sucesso!");
  } catch (error) {
    console.error("Erro ao atualizar documento: ", error);
  }
}

//atualizarDocumento("e-62941000", ModelActual)

const ModelResp = {
  resposta: "No podcast “Ataques em escolas e creches” do Inteligência Ltda., discute-se a preocupante frequência de ataques em instituições de ensino no Brasil e no mundo. Os apresentadores abordam os aspectos psicológicos e sociais que levam a esses atos de violência, além de discutir as possíveis medidas para prevenção e intervenção..",
  autor: "Jaime Beatriz  Manhica filho da mama",
  dataDaReposta: dataHorafunction()
}

// funcao de resposta
async function responderDocumento(idDocumento, dadosAtualizados) {
  try {

    var code = await generateUniqueCode();
    const novoPost = doc(db, "postagens", idDocumento, "respostas", code.toString());

    // Set the "capital" field of the city 'DC'
    await setDoc(novoPost, dadosAtualizados);
    console.log("Documento comentado com sucesso!");
  } catch (error) {
    console.error("Erro ao atualizar documento: ", error);
  }
}
//responderDocumento("e-67231000",ModelResp)

// // Função para excluir um documento existente
async function excluirDocumento(idDocumento) {
  try {
    const status = doc(db, "postagens", idDocumento);

    // Set the "capital" field of the city 'DC'
    await updateDoc(status, {
      status: false
    });
    console.log("Documento excluído com sucesso!");
  } catch (error) {
    console.error("Erro ao excluir documento: ", error);
  }
}
 //excluirDocumento("e-67231000")






/*
async function buscarTodos() {
  const querySnapshot = await getDocs(collection(db, "postagens"));
  querySnapshot.forEach((doc) => {
    // doc.data() is never undefined for query doc snapshots
    console.log(doc.id, " => ", doc.data());
  })
}*/

//buscarTodos()