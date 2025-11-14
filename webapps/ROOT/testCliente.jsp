<%@page import="com.urbancut.models.Cliente"%>
<%@page import="com.urbancut.repositories.ClienteRepository"%>
<%

        Cliente cliente = new Cliente.ClienteBuilder()
                .nome("Giovanne")
                .email("giovanne@gmail.com")
                .senha("123456")
                .build();

        out.print("<h2>Criando o cliente</h2>");
        try {
            (new ClienteRepository()).save(cliente);
            out.print("Criado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        if (request.getParameter("id") != null) {
                out.print("<br><br>====================");
                out.print("<h2>Buscando o cliente via ID</h2>");
                try {
                    Cliente resposta = (new ClienteRepository()).searchById(Integer.parseInt(request.getParameter("id")));
                    out.print(resposta);
                } catch (Exception e) {
                    out.print(e.getMessage());
                }
        }

        out.print("<br><br>====================");
        out.print("<h2>Buscando o cliente via EMAIL</h2>");
        try {
            Cliente resposta = (new ClienteRepository()).searchByEmail("giovanne@gmail.com");
            out.print(resposta);
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        out.print("<br><br>====================");
        out.print("<h2>Atualizando o cliente</h2>");
        try {
            cliente = (new ClienteRepository()).searchByEmail("giovanne@gmail.com");

            cliente.setNome("Giovanne Monteiro");

            (new ClienteRepository()).update(cliente);

            out.print("Atualizado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

%>