const profilePictureInput = document.getElementById('profile-picture-input');
const profilePicture = document.getElementById('profile-picture');

// Verifica se a imagem de perfil está armazenada no localStorage
const storedImage = localStorage.getItem('selectedImage');

if (storedImage) {
    profilePicture.src = storedImage;
} else {
    // Define uma imagem padrão caso não haja imagem de perfil
   profilePicture.src ='../icons/userpost.png' 
}

profilePictureInput.addEventListener('change', (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (e) => {
        const imageBase64 = e.target.result;
        profilePicture.src = imageBase64;

        // Armazena a imagem codificada em base64 no localStorage
        localStorage.setItem('selectedImage', imageBase64);
    };

    reader.readAsDataURL(file);
});

// Função para remover a foto de perfil
function removeProfilePicture() {
    profilePicture.src = '';

    // Remove a imagem armazenada do localStorage
    localStorage.removeItem('selectedImage');
}

// Obtenha os dados do usuário logado no Firebase
firebase.auth().onAuthStateChanged((user) => {
    if (user) {
        // O usuário está logado, você pode acessar os dados do usuário usando user.uid, user.displayName, etc.
        const userId = user.uid;
        const userName = user.displayName;

        // Verificar se a foto de perfil está presente no Firestore
        firebase.firestore().collection('users').doc(userId).get()
            .then((doc) => {
                if (doc.exists) {
                    const userData = doc.data();
                    if (userData.profilePicture) {
                        // A foto de perfil está presente no Firestore
                        profilePicture.src = userData.profilePicture;
                    } else {
                        // Verificar se a foto de perfil está presente no Storage
                        const storageRef = firebase.storage().ref('profile-pictures').child(`${userId}.jpg`);
                        storageRef.getDownloadURL()
                            .then((url) => {
                                // A foto de perfil está presente no Storage
                                profilePicture.src = url;
                            })
                            .catch((error) => {
                                // A foto de perfil não está presente no Storage, usar a imagem padrão
                                profilePicture.src = '../icons/userpost.png';
                            });
                    }
                } else {
                    // Documento do usuário não encontrado no Firestore, usar a imagem padrão
                    profilePicture.src = '../icons/userpost.png';
                }
            })
            .catch((error) => {
                console.log('Erro ao obter os dados do usuário:', error);
            });
    } else {
        // O usuário não está logado
        console.log('Usuário não está logado');
    }
});
