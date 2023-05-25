
    const profilePictureInput = document.getElementById('profile-picture-input');
    const profilePicture = document.getElementById('profile-picture');

    // Verifica se a imagem está armazenada no localStorage
    const storedImage = localStorage.getItem('selectedImage');

    if (storedImage) {
        profilePicture.src = storedImage;
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

    function removeProfilePicture() {
        profilePicture.src = '';

        // Remove a imagem armazenada do localStorage
        localStorage.removeItem('selectedImage');
    }

