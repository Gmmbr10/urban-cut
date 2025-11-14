package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.AuthenticableInterface;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.Barbeiro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BarbeiroRepository extends Repository implements RepositoryInterface<Barbeiro>, AuthenticableInterface<Barbeiro> {
    @Override
    public boolean save(Barbeiro model) throws SQLException {
        String query = "INSERT INTO barbeiros (nome,email,senha,id_barbearia) VALUES (?,?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getEmail());
        stm.setString(3, model.getSenha());
        stm.setInt(4, model.getIdBarbearia());

        return stm.execute();
    }

    @Override
    public Barbeiro searchById(int id) throws SQLException {
        Barbeiro barbeiro = null;

        String query = "SELECT * FROM barbeiros WHERE id_barbeiro = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            barbeiro = new Barbeiro.BarbeiroBuilder().idBarbeiro(data.getInt("id_barbeiro")).idBarbearia(data.getInt("id_barbearia")).nome(data.getString("nome")).senha(data.getString("senha")).email(data.getString("email")).build();
        }

        return barbeiro;
    }

    @Override
    public boolean update(Barbeiro model) throws SQLException {
        String query = "UPDATE barbeiros SET nome=?,email=?,senha=?,id_barbearia=? WHERE id_barbeiro = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getEmail());
        stm.setString(3, model.getSenha());
        stm.setInt(4, model.getIdBarbearia());
        stm.setInt(5, model.getIdBarbearia());

        return stm.execute();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM barbeiros WHERE id_barbeiro = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        return stm.execute();
    }

    @Override
    public Barbeiro searchByEmail(String email) throws SQLException {
        Barbeiro barbeiro = null;

        String query = "SELECT * FROM barbeiros WHERE email = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, email);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            barbeiro = new Barbeiro.BarbeiroBuilder().idBarbeiro(data.getInt("id_barbeiro")).idBarbearia(data.getInt("id_barbearia")).nome(data.getString("nome")).senha(data.getString("senha")).email(data.getString("email")).build();
        }

        return barbeiro;
    }
}
