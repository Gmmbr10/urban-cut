<%@page import="java.util.Arrays"%>
<%@page import="com.urbancut.models.Barbearia"%>
<%@page import="com.urbancut.repositories.BarbeariaRepository"%>
<%

        Barbearia barbearia = new Barbearia.BarbeariaBuilder()
            .idDono(Integer.parseInt(request.getParameter("idBarbeiro")))
            .nome("Barbearia do giovanne")
            .urlMaps("url")
            .tempoMedioAtendimento(40)
            .build();

        out.print("<h2>Criando a barbearia</h2>");
        try {
            (new BarbeariaRepository()).save(barbearia);
            out.print("Criado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        if (request.getParameter("id") != null) {
            out.print("<br><br>====================");
            out.print("<h2>Buscando a barbearia via ID</h2>");
            try {
                Barbearia resposta = (new BarbeariaRepository()).searchById(Integer.parseInt(request.getParameter("id")));
                out.print(resposta);
                out.print("<br>");
                out.print(Arrays.toString(resposta.getBarbeiros()));
            } catch (Exception e) {
                out.print(e.getMessage());
            }
        }

%>