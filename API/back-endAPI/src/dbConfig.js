// Importando as funções necessárias dos SDKs do Firebase
const { initializeApp } = require('firebase/app');
const { getFirestore } = require('firebase/firestore');

// Sua configuração do Firebase para o aplicativo da web
const firebaseConfig = {
    apiKey: "AIzaSyBrmn8ny89UZtmOEN1fEj1QHxE5aB-oA18", // Chave da API do Firebase para autenticação e autorização
    authDomain: "itens-perdidos.firebaseapp.com", // Domínio autorizado para autenticação
    databaseURL: "https://itens-perdidos-default-rtdb.firebaseio.com",  // URL da base de dados em tempo real
    projectId: "itens-perdidos", // ID do projeto Firebase
    storageBucket: "itens-perdidos.appspot.com",// Bucket de armazenamento para armazenar arquivos e mídia
    messagingSenderId: "802987114080", // ID do remetente de mensagens para notificações push
    appId: "1:802987114080:web:4ccec22fc31eb69724cd05", // ID do aplicativo Firebase
    measurementId: "G-1M888NRELC"// ID de medição opcional para o Firebase Analytics
};

// Inicializa o app do Firebase com a configuração fornecida
const app = initializeApp(firebaseConfig);

// Obtém uma instância do Firestore a partir do app inicializado
const db = getFirestore(app);


// Método para obter informações da conexão com a base de dados
function getDatabaseConnectionInfo() {
    // Obtém o nome do projeto
    const projectId = app.options.projectId;

    // Obtém a URL da base de dados
    const databaseURL = app.options.databaseURL;

    // Obtém o ID do aplicativo
    const appId = app.options.appId;

    // Retorna uma mensagem com os detalhes da conexão
    return `Conectado ao projeto '${projectId}' do fireStore, na base de dados '${databaseURL}' (ID do aplicativo: ${appId})`;
}

const connectionInfo = getDatabaseConnectionInfo();
console.log(connectionInfo);
// Exporta a instância do Firestore para uso em outros arquivos
module.exports = db;
