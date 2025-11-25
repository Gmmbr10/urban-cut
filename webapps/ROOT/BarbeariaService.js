const baseURL = "http://localhost:8080/barbearia";

function getFormData() {
    return new FormData(document.getElementById("form"));
}

async function cadastrar() {
    const data = await fetch(baseURL + "/register", {
        method: "POST",
        body: getFormData()
    });
    document.getElementById("resultado").textContent = await data.text();
}

async function buscar() {
    const data = await fetch(baseURL + "/searchById", {
        method: "POST",
        body: getFormData()
    });
    document.getElementById("resultado").textContent = await data.text();
}

async function atualizar() {
    const data = await fetch(baseURL + "/update", {
        method: "POST",
        body: getFormData()
    });
    document.getElementById("resultado").textContent = await data.text();
}

async function deletar() {
    const formData = new FormData();
    formData.append("idBarbearia", document.querySelector("input[name='idBarbearia']").value);

    const data = await fetch(baseURL + "/delete", {
        method: "POST",
        body: formData
    });
    document.getElementById("resultado").textContent = await data.text();
}
