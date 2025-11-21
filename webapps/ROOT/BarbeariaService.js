document.getElementById("formBarbearia").addEventListener("submit", async function(e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    try {
        const response = await fetch("http://localhost:8080/barbearia/register", {
            method: "POST",
            body: formData
        });

        const result = await response.json();
        document.getElementById("mensagem").innerText = result.message 
            ? result.message 
            : "Barbearia cadastrada com sucesso!";

        if (response.status === 201) {
            form.reset();
        }

    } catch (erro) {
        document.getElementById("mensagem").innerText = "Erro ao conectar com o servidor.";
    }
});
