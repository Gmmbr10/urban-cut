package com.urbancut.services;

import com.urbancut.core.Response;
import com.urbancut.models.Barbearia;
import com.urbancut.models.Barbeiro;
import com.urbancut.models.Cliente;
import com.urbancut.repositories.BarbeariaRepository;
import com.urbancut.repositories.BarbeiroRepository;
import com.urbancut.repositories.ClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;

public class AuthService {
    public Response<Boolean> login(HttpServletRequest request, HttpSession session) {

        if (request.getParameter("email").isBlank() || request.getParameter("senha").isBlank()) {
            return new Response<>(400, "Preencha os campos!", false);
        }

        switch (request.getParameter("tipo").toLowerCase()) {
            case "cliente":
                try {
                    Cliente cliente = new ClienteRepository().searchByEmail(request.getParameter("email"));

                    if (!cliente.getSenha().equals(request.getParameter("senha"))) {
                        return new Response<>(400, "Email ou senha incorreto!", false);
                    }

                    session.setAttribute("id", cliente.getIdCliente());
                    session.setAttribute("rule", "cliente");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "barbeiro":
                try {
                    Barbeiro barbeiro = new BarbeiroRepository().searchByEmail(request.getParameter("email"));

                    if (!barbeiro.getSenha().equals(request.getParameter("senha"))) {
                        return new Response<>(400, "Email ou senha incorreto!", false);
                    }

                    session.setAttribute("id", barbeiro.getIdBarbeiro());

                    if (barbeiro.getIdBarbearia() != null) {
                        session.setAttribute("hasBarbearia",true);

                        Barbearia barbearia = new BarbeariaRepository().searchById(barbeiro.getIdBarbearia());

                        if (barbearia.getIdDono().equals(barbeiro.getIdBarbeiro())) {
                            session.setAttribute("isDono",true);
                        } else {
                            session.setAttribute("isDono",false);
                        }
                    } else {
                        session.setAttribute("hasBarbearia",false);
                    }

                    session.setAttribute("rule", "barbeiro");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                return new Response<>(400, "Preencha todos os campos!", false);
        }

        return new Response<>(204, true);
    }

    public Response<Boolean> logout(HttpSession session) {
        if (isLogged(session)) {
            session.invalidate();
        }

        return new Response<>(204, true);
    }

    public boolean isLogged(HttpSession session) {
        return session.getAttribute("id") != null;
    }

    public boolean isThatRule(HttpSession session, String rule) {
        if (session.getAttribute("rule") == null) {
            return false;
        }
        
        return session.getAttribute("rule").equals(rule.toLowerCase());
    }
}
