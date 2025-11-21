package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.Atendimento;

import java.sql.*;

public class AtendimentoRepository extends Repository implements RepositoryInterface<Atendimento> {
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM atendimentos WHERE id_atendimento = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }

    @Override
    public void update(Atendimento model) throws SQLException {
        String query = "UPDATE atendimentos SET atendimento=? WHERE id_atendimento = ?";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setTimestamp(1, Timestamp.valueOf(model.getAtendimento()));
        stm.setInt(2, model.getIdAtendimento());

        stm.execute();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }

    @Override
    public Atendimento searchById(int id) throws SQLException {
        Atendimento atendimento = null;

        String query = "SELECT * FROM atendimentos WHERE id_atendimento = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            atendimento = new Atendimento.AtendimentoBuilder()
                    .idAtendimento(data.getInt("id_atendimento"))
                    .idBarbearia(data.getInt("id_barbearia"))
                    .idBarbeiro(data.getInt("id_barbeiro"))
                    .idCliente(data.getInt("id_cliente"))
                    .Atendimento(data.getTimestamp("atendimento").toLocalDateTime())
                    .build();
        }

        return atendimento;
    }

    @Override
    public void save(Atendimento model) throws SQLException {
        String query = "INSERT INTO atendimentos (id_barbearia,id_barbeiro,id_cliente,atendimento) VALUES (?,?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setInt(1, model.getIdBarbearia());
        stm.setInt(2, model.getIdBarbeiro());
        stm.setInt(3, model.getIdCliente());
        stm.setTimestamp(4, Timestamp.valueOf(model.getAtendimento()));

        stm.execute();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();
    }
}
