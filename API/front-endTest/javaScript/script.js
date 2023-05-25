// Função para obter os posts da API
async function getPosts() {
    try {
        const response = await fetch('http://localhost:3000/em/posts');
        const data = await response.json();

        return data; // Supondo que a resposta da API tenha uma propriedade "posts" com os dados dos posts
    } catch (error) {
        console.log(error);
        return [];
    }
}

// Função para criar um elemento de post com base nos dados

function createPostElement(post) {
    console.log(post)
    const postElement = document.createElement('div');
    postElement.classList.add('post');

    const postHeader = document.createElement('div');
    postHeader.classList.add('post-header');

    const authorImage = document.createElement('img');
    authorImage.alt = '';

    if (post.authorImage) {
        authorImage.src = post.authorImage;
    } else {
        authorImage.src = '../icons/user.png'; // Substitua pelo caminho correto da imagem padrão desejada
    }


    const authorInfo = document.createElement('div');
    authorInfo.classList.add('post-author-info');

    const authorName = document.createElement('h4');
    authorName.textContent = post.data.autor;

    const postDate = document.createElement('p');
    postDate.textContent = post.data.dataPost;

    const postContent = document.createElement('div');
    postContent.id = 'post-content';
    postContent.textContent = post.data.Post;

    const postActions = document.createElement('div');
    postActions.classList.add('post-actions');

    const commentButton = document.createElement('button');
    commentButton.textContent = 'Comentar';

    postHeader.appendChild(authorImage);
    authorInfo.appendChild(authorName);
    authorInfo.appendChild(postDate);
    postHeader.appendChild(authorInfo);
    postElement.appendChild(postHeader);
    postElement.appendChild(postContent);
    postActions.appendChild(commentButton);
    postElement.appendChild(postActions);

    return postElement;
}

// Função para exibir os posts na página
async function showPosts() {

    const postsContainer = document.getElementById('posts-container');
    let posts = await getPosts();
    console.log("12", posts)
    getPosts().then(response => {
        response.forEach(post => {
            const postElement = createPostElement(post);
            postsContainer.appendChild(postElement);
        });
    })

}

// Chama a função para exibir os posts quando a página carrega
window.addEventListener('load', showPosts);
