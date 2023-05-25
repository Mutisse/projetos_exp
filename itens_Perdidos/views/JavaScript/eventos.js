 // Seleciona os elementos do HTML
const editar = document.querySelectorAll("button")[1];
const criar = document.getElementById('criar');

const documento = ["BI", "NUIT", "ETC"];
// Evento para criar um novo item
criar.addEventListener('click', () => {
// Código para criar um novo item
    alert("Fui clicado")

});

editar.addEventListener("click", () => {
    var texto = editar.innerText;
     if(texto == "Editar") {
        editar.innerText = "Actualizar"
     } else {
        editar.innerText = "Editar"
     }
});
/*eliminar.Eliminar.addEventListener('click', () => {
// Código para criar um novo item
    alert(" fui clicado")
});


// Evento para editar um item
editar.addEventListener('click', () => {
    alert(" fui clicado")
// Código para editar um item
// Exibe o botão de atualizar e esconde o botão de editar
    atualizar.atualizar
    style.display = 'block';
    editar.style.display = 'none';
});

// Evento para atualizar um item
atualizar.addEventListener('click', () => {
// Código para atualizar um item

    alert(" fui clicado")
// Exibe o botão de editar e esconde o botão de atualizar
    editar.style.display = 'block';})*/
