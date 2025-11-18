<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="com.urbancut.models.ResumoDia"%>
<%@page import="com.urbancut.repositories.ResumoDiaRepository"%>
<%

    List<ResumoDia> resumos = (new ResumoDiaRepository()).summary(LocalDate.parse("2025-12-20"),4);

    for (ResumoDia r : resumos) {
        out.print(r.getCliente());
        out.print("<br>");
        out.print(r.getData());
        out.print("<br>");
        out.print(r.getHorario());
        out.print("<br>====================<br>");
    }

%>