package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.models.Barbeiro;
import com.urbancut.models.Cliente;
import com.urbancut.repositories.BarbeiroRepository;
import com.urbancut.repositories.ClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public class AuthService {
    public Response<Boolean> login(HttpServletRequest request, HttpSession session){
        switch(request.getParameter("tipo")){
            case "Cliente":
                try {
                    Cliente cliente = new ClienteRepository().searchByEmail(request.getParameter("email"));

                    if (!cliente.getSenha().equals(request.getParameter("senha"))) {
                        return new Response<>(400,"Email ou senha incorreto!",false);
                    }

                    session.setAttribute("id",cliente.getIdCliente());
                    session.setAttribute("rule","cliente");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "Barbeiro":
                try {
                    Barbeiro barbeiro = new BarbeiroRepository().searchByEmail(request.getParameter("email"));

                    if (!barbeiro.getSenha().equals(request.getParameter("senha"))) {
                        return new Response<>(400,"Email ou senha incorreto!",false);
                    }

                    session.setAttribute("id",barbeiro.getIdBarbeiro());
                    session.setAttribute("rule","barbeiro");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                return new Response<>(400,"Falta de informações!",false);
        }

        return new Response<>(204,true);
    }

    public Response<Boolean> logout(HttpSession session){
        if (isLogged(session)){
            session.invalidate();
        }

        return new Response<>(204,true);
    }

    public boolean isLogged(HttpSession session){
        return session != null;
    }

    public boolean isThatRule(HttpSession session,String rule){
        return session.getAttribute("rule").equals(rule.toLowerCase());
    }
}
