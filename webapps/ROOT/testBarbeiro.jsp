<%@page import="com.urbancut.models.Barbeiro"%>
<%@page import="com.urbancut.repositories.BarbeiroRepository"%>
<%

        Barbeiro barbeiro = new Barbeiro.BarbeiroBuilder()
                .nome("Giovanne")
                .email("giovanne@gmail.com")
                .senha("123456")
                .build();

        out.print("<h2>Criando o barbeiro</h2>");
        try {
            (new BarbeiroRepository()).save(barbeiro);
            out.print("Criado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        if (request.getParameter("id") != null) {
                out.print("<br><br>====================");
                out.print("<h2>Buscando o barbeiro via ID</h2>");
                try {
                    Barbeiro resposta = (new BarbeiroRepository()).searchById(Integer.parseInt(request.getParameter("id")));
                    out.print(resposta);
                } catch (Exception e) {
                    out.print(e.getMessage());
                }
        }

        out.print("<br><br>====================");
        out.print("<h2>Buscando o barbeiro via EMAIL</h2>");
        try {
            Barbeiro resposta = (new BarbeiroRepository()).searchByEmail("giovanne@gmail.com");
            out.print(resposta);
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        out.print("<br><br>====================");
        out.print("<h2>Atualizando o barbeiro</h2>");
        try {
            barbeiro = (new BarbeiroRepository()).searchByEmail("giovanne@gmail.com");

            barbeiro.setNome("Giovanne Monteiro");

            (new BarbeiroRepository()).update(barbeiro);

            out.print("Atualizado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

%>