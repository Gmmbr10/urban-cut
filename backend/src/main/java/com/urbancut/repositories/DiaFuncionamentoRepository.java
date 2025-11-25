package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.DiaFuncionamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaFuncionamentoRepository extends Repository implements RepositoryInterface<DiaFuncionamento> {
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM dias_funcionamento WHERE id_dia_funcionamento = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }

    @Override
    public void update(DiaFuncionamento model) throws SQLException {
        String query = "UPDATE dias_funcionamento SET dia_semana=?, hora_abertura=?, hora_fechamento=? WHERE id_dia_funcionamento=? AND id_barbearia=?";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, model.getDiaSemana());
        stm.setTime(2, Time.valueOf(model.getHoraAbertura()));
        stm.setTime(3, Time.valueOf(model.getHoraFechamento()));
        stm.setInt(4, model.getIdDiaFuncionamento());
        stm.setInt(5, model.getIdBarbearia());

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }

    @Override
    public DiaFuncionamento searchById(int id) throws SQLException {
        DiaFuncionamento dias = null;

        String query = "SELECT * FROM dias_funcionamento WHERE id_dia_funcionamento = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            dias = new DiaFuncionamento.DiasFuncionamentoBuilder().idDiaFuncionamento(data.getInt("id_dia_funcionamento")).idBarbearia(data.getInt("id_barbearia")).diaSemana(data.getString("dia_semana")).horaAbertura(data.getTime("hora_abertura").toLocalTime()).horaFechamento(data.getTime("hora_fechamento").toLocalTime()).build();
        }

        return dias;
    }

    @Override
    public void save(DiaFuncionamento model) throws SQLException {
        String query = "INSERT INTO dias_funcionamento (id_barbearia, dia_semana, hora_abertura, hora_fechamento) VALUES (?,?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setInt(1, model.getIdBarbearia());
        stm.setString(2, model.getDiaSemana());
        stm.setTime(3, Time.valueOf(model.getHoraAbertura()));
        stm.setTime(4, Time.valueOf(model.getHoraFechamento()));

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }

    public List<DiaFuncionamento> searchByBarbearia(int idBarbearia) throws SQLException {
        List<DiaFuncionamento> diasFuncionamentoList = new ArrayList<>();

        String query = "SELECT * FROM dias_funcionamento WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, idBarbearia);

        ResultSet data = stm.executeQuery();

        while (data.next()) {
            DiaFuncionamento dia = new DiaFuncionamento.DiasFuncionamentoBuilder().idDiaFuncionamento(data.getInt("id_dia_funcionamento")).idBarbearia(data.getInt("id_barbearia")).diaSemana(data.getString("dia_semana")).horaAbertura(data.getTime("hora_abertura").toLocalTime()).horaFechamento(data.getTime("hora_fechamento").toLocalTime()).build();

            diasFuncionamentoList.add(dia);
        }

        return diasFuncionamentoList;
    }
}
