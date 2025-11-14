package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.Barbearia;
import com.urbancut.models.Barbeiro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarbeariaRepository extends Repository implements RepositoryInterface<Barbearia> {
    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM barbearias WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }

    @Override
    public void update(Barbearia model) throws SQLException {
        String query = "UPDATE barbearias SET nome=?,url_maps=?,tempo_medio=? WHERE id_barbearia = ? AND id_dono = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getUrlMaps());
        stm.setTime(3, Time.valueOf(model.getTempoMedioAtendimento()));
        stm.setInt(4, model.getIdBarbearia());
        stm.setInt(5, model.getIdDono());

        stm.execute();
    }

    @Override
    public Barbearia searchById(int id) throws SQLException {
        Barbearia barbearia = null;

        String query = "SELECT * FROM barbearias WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            barbearia = new Barbearia.BarbeariaBuilder().idBarbearia(data.getInt("id_barbearia")).idDono(data.getInt("id_dono")).tempoMedioAtendimento(data.getTime("tempo_medio").toLocalTime()).urlMaps(data.getString("url_maps")).nome(data.getString("nome")).build();

            barbearia.setBarbeiros(this.searchBarbeiros(id));
        }

        return barbearia;
    }

    private Barbeiro[] searchBarbeiros(int id) throws SQLException {
        List<Barbeiro> barbeiros = new ArrayList<>();

        String query = "SELECT * FROM barbeiros WHERE id_barbearia = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        while (data.next()) {
            Barbeiro barbeiro = new Barbeiro.BarbeiroBuilder().idBarbeiro(data.getInt("id_barbeiro")).idBarbearia(data.getInt("id_barbearia")).nome(data.getString("nome")).senha(data.getString("senha")).email(data.getString("email")).build();
            barbeiros.add(barbeiro);
        }


        return barbeiros.toArray(new Barbeiro[0]);
    }

    @Override
    public void save(Barbearia model) throws SQLException {
        String query = "INSERT INTO barbearias (id_dono,nome,url_maps,tempo_medio) VALUES (?,?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setInt(1, model.getIdDono());
        stm.setString(2, model.getNome());
        stm.setString(3, model.getUrlMaps());
        stm.setTime(4, Time.valueOf(model.getTempoMedioAtendimento()));

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        resultSet.next();

        Barbeiro b = (new BarbeiroRepository()).searchById(model.getIdDono());
        b.setIdBarbearia(resultSet.getInt(1));
        (new BarbeiroRepository()).update(b);
    }
}
