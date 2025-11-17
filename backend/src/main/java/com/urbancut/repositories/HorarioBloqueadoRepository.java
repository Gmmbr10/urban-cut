package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.HorarioBloqueado;

import java.sql.*;

public class HorarioBloqueadoRepository extends Repository implements RepositoryInterface<HorarioBloqueado> {
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM horarios_bloqueados WHERE id_horario_bloqueado = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }

    @Override
    public void update(HorarioBloqueado model) throws SQLException {
        String query = "UPDATE horarios_bloqueados SET inicio=?,fim=? WHERE id_horario_bloqueado=? AND id_barbeiro=?";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setTime(1, Time.valueOf(model.getInicio()));
        stm.setTime(2, Time.valueOf(model.getFim()));
        stm.setInt(3, model.getIdHorarioBloqueado());
        stm.setInt(4, model.getIdBarbeiro());

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }

    @Override
    public HorarioBloqueado searchById(int id) throws SQLException {
        HorarioBloqueado horarioBloqueado = null;
        String query = "SELECT * FROM horarios_bloqueados WHERE id_barbeiro = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()){
            horarioBloqueado = new HorarioBloqueado.HorarioBloqueadoBuilder()
                    .idHorarioBloqueado(data.getInt("id_horario_bloqueado"))
                    .idBarbeiro(data.getInt("id_barbeiro"))
                    .inicio(data.getTime("inicio").toLocalTime())
                    .fim(data.getTime("fim").toLocalTime())
                    .build();
        }
        return horarioBloqueado;
    }

    @Override
    public void save(HorarioBloqueado model) throws SQLException {
        String query = "INSERT INTO horarios_bloqueados (id_barbeiro,inicio,fim) VALUES (?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setInt(1, model.getIdBarbeiro());
        stm.setTime(2, Time.valueOf(model.getInicio()));
        stm.setTime(3, Time.valueOf(model.getFim()));

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }
}
