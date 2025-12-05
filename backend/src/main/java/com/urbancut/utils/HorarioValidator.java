package com.urbancut.utils;

import com.urbancut.models.Barbearia;
import com.urbancut.models.Barbeiro;
import com.urbancut.models.DiaFuncionamento;
import com.urbancut.repositories.BarbeariaRepository;
import com.urbancut.repositories.BarbeiroRepository;
import com.urbancut.repositories.DiaFuncionamentoRepository;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class HorarioValidator {
    public static boolean isDentroDoHorarioBloqueado(LocalDateTime dataHora, int idBarbeiro) throws SQLException {
        Barbeiro barbeiro = new BarbeiroRepository().searchById(idBarbeiro);

        if (barbeiro == null || barbeiro.getHorarioBloqueado() == null) return false;

        boolean isDentroDoHorarioBloqueado = barbeiro.getHorarioBloqueado().getInicio().minusMinutes(1).isBefore(dataHora.toLocalTime()) && barbeiro.getHorarioBloqueado().getFim().isAfter(dataHora.toLocalTime());

        return isDentroDoHorarioBloqueado;
    }

    public static boolean isDentroDoHorarioDaBarbearia(LocalDateTime dataHora, int idBarbearia) throws SQLException {
        Barbearia barbearia = new BarbeariaRepository().searchById(idBarbearia);

        if (barbearia == null) return false;

        List<DiaFuncionamento> diasFuncionamento = new DiaFuncionamentoRepository().searchByBarbearia(barbearia.getIdBarbearia());

        DayOfWeek dia = dataHora.toLocalDate().getDayOfWeek();

        for (DiaFuncionamento diaFuncionamento : diasFuncionamento) {
            switch (dia) {
                case SUNDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Domingo")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case MONDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Segunda-Feira")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case TUESDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Terça-Feira")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case WEDNESDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Quarta-Feira")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case THURSDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Quinta-Feira")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case FRIDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Sexta-Feira")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
                case SATURDAY:
                    if (!diaFuncionamento.getDiaSemana().equals("Sábado")) {
                        continue;
                    }

                    return diaFuncionamento.getHoraAbertura().minusMinutes(1).isBefore(dataHora.toLocalTime()) && diaFuncionamento.getHoraFechamento().isAfter(dataHora.toLocalTime());
            }
        }

        return false;
    }
}
