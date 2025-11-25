package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.RepositoryWithReturnInterface;
import com.urbancut.models.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EnderecoRepository extends Repository implements RepositoryWithReturnInterface<Endereco> {
    @Override
    public void save(Endereco model) throws SQLException {
        String query = "INSERT INTO enderecos (cep, estado, cidade, bairro, logradouro, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, model.getCep());
        stm.setString(2, model.getEstado());
        stm.setString(3, model.getCidade());
        stm.setString(4, model.getBairro());
        stm.setString(5, model.getLogradouro());
        stm.setInt(6, model.getNumero());
        stm.setString(7, model.getComplemento());

        stm.executeUpdate();
    }

    @Override
    public int saveWithReturn(Endereco model) throws SQLException {
        String query = "INSERT INTO enderecos (cep, estado, cidade, bairro, logradouro, numero, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stm = this.database.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        stm.setString(1, model.getCep());
        stm.setString(2, model.getEstado());
        stm.setString(3, model.getCidade());
        stm.setString(4, model.getBairro());
        stm.setString(5, model.getLogradouro());
        stm.setInt(6, model.getNumero());
        stm.setString(7, model.getComplemento());

        stm.executeUpdate();

        ResultSet resultSet = stm.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }

    @Override
    public Endereco searchById(int id) throws SQLException {
        Endereco endereco = null;

        String query = "SELECT * FROM enderecos WHERE id_endereco = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            endereco = new Endereco.EnderecoBuilder().idEndereco(data.getInt("id_endereco")).cep(data.getString("cep")).estado(data.getString("estado")).cidade(data.getString("cidade")).bairro(data.getString("bairro")).logradouro(data.getString("logradouro")).numero(data.getInt("numero")).complemento(data.getString("complemento")).build();
        }

        return endereco;
    }

    @Override
    public void update(Endereco model) throws SQLException {
        String query = "UPDATE enderecos SET cep=?, estado=?, cidade=?, bairro=?,logradouro=?, numero=?, complemento=? WHERE id_endereco = ?";

        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getCep());
        stm.setString(2, model.getEstado());
        stm.setString(3, model.getCidade());
        stm.setString(4, model.getBairro());
        stm.setString(5, model.getLogradouro());
        stm.setInt(6, model.getNumero());
        stm.setString(7, model.getComplemento());
        stm.setInt(8, model.getIdEndereco());

        stm.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM enderecos WHERE id_endereco = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }
}
