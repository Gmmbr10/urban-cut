<%@page import="java.time.LocalTime"%>
<%@page import="com.urbancut.models.HorarioBloqueado"%>
<%@page import="com.urbancut.repositories.HorarioBloqueadoRepository"%>
<%

        HorarioBloqueado horarioBloqueado = new HorarioBloqueado.HorarioBloqueadoBuilder()
            .idBarbeiro(1)
            .inicio(LocalTime.parse("10:00"))
            .fim(LocalTime.parse("17:00"))
            .build();

        out.print("<h2>Criando a barbearia</h2>");
        try {
            (new HorarioBloqueadoRepository()).save(horarioBloqueado);
            out.print("Criado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

        out.print("<br><br>====================");
        out.print("<h2>Atualizando horário bloqueado</h2>");

        try {
            horarioBloqueado = (new HorarioBloqueadoRepository()).searchById(1);

            horarioBloqueado.setInicio(LocalTime.parse("10:00"));
            horarioBloqueado.setFim(LocalTime.parse("17:00"));

            (new HorarioBloqueadoRepository()).update(horarioBloqueado);

            out.print("Atualizado!!!");
        } catch (Exception e) {
            out.print(e.getMessage());
        }

%>