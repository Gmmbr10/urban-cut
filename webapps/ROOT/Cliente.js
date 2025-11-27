const formLogin = document.forms["login_cliente"];
const erroLogin = formLogin.querySelector("span");

formLogin.addEventListener("submit", function(event) {
    event.preventDefault();

    let email = formLogin.email.value.trim();
    let senha = formLogin.senha.value.trim();

    if (!email || !senha) {
        erroLogin.textContent = "Preencha todos os campos!";
        erroLogin.style.visibility = "visible";
        return;
    }

    erroLogin.style.visibility = "hidden"; 
    alert("Login realizado com sucesso!");
});

const formCadastro = document.forms["cadastrar_cliente"];
const erroCadastro = formCadastro.querySelector("p");

formCadastro.addEventListener("submit", function(event) {
    event.preventDefault();

    let nome = formCadastro.nome.value.trim();
    let email = formCadastro.email.value.trim();
    let senha = formCadastro.senha.value.trim();

    if (!nome || !email || !senha) {
        erroCadastro.textContent = "Preencha todos os campos!";
        erroCadastro.style.visibility = "visible";
        return;
    }

    erroCadastro.style.visibility = "hidden";
    alert("Cadastro realizado com sucesso!");
});
