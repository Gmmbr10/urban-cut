package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.Barbearia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class BarbeariaRepository extends Repository implements RepositoryInterface<Barbearia> {
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM barbearias WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        return stm.execute();
    }

    @Override
    public boolean update(Barbearia model) throws SQLException {
        String query = "UPDATE barbearias SET nome=?,url_maps=?,tempo_medio=? WHERE id_barbearia = ? AND id_dono = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getUrlMaps());
        stm.setTime(3, Time.valueOf(model.getTempoMedioAtendimento()));
        stm.setInt(4, model.getIdBarbearia());
        stm.setInt(5, model.getIdDono());

        return stm.execute();
    }

    @Override
    public Barbearia searchById(int id) throws SQLException {
        Barbearia barbearia = null;

        String query = "SELECT * FROM barbearias WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            barbearia = new Barbearia.BarbeariaBuilder()
                    .idBarbearia(data.getInt("id_barbearia"))
                    .idDono(data.getInt("id_dono"))
                    .tempoMedioAtendimento(data.getTime("tempo_medio").toLocalTime())
                    .urlMaps(data.getString("url_maps"))
                    .nome(data.getString("nome"))
                    .build();
        }

        return barbearia;
    }

    @Override
    public boolean save(Barbearia model) throws SQLException {
        String query = "INSERT INTO barbearias (id_dono,nome,url_maps,tempo_medio) VALUES (?,?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, model.getIdDono());
        stm.setString(2, model.getNome());
        stm.setString(3, model.getUrlMaps());
        stm.setTime(4, Time.valueOf(model.getTempoMedioAtendimento()));

        return stm.execute();
    }
}
