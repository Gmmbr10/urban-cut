package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.core.interfaces.AuthenticableInterface;
import com.urbancut.core.interfaces.RepositoryInterface;
import com.urbancut.models.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepository extends Repository implements RepositoryInterface<Cliente>, AuthenticableInterface<Cliente> {
    @Override
    public void save(Cliente model) throws SQLException {
        String query = "INSERT INTO clientes (nome,email,senha) VALUES (?,?,?)";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getEmail());
        stm.setString(3, model.getSenha());

        stm.execute();
    }

    @Override
    public Cliente searchById(int id) throws SQLException {
        Cliente cliente = null;

        String query = "SELECT * FROM clientes WHERE id_cliente = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            cliente = new Cliente.ClienteBuilder().idCliente(data.getInt("id_cliente")).nome(data.getString("nome")).senha(data.getString("senha")).email(data.getString("email")).build();
        }

        return cliente;
    }

    @Override
    public void update(Cliente model) throws SQLException {
        String query = "UPDATE clientes SET nome=?,email=?,senha=? WHERE id_cliente =?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, model.getNome());
        stm.setString(2, model.getEmail());
        stm.setString(3, model.getSenha());
        stm.setInt(4, model.getIdCliente());

        stm.execute();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM clientes WHERE id_cliente = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1, id);

        stm.execute();
    }

    @Override
    public Cliente searchByEmail(String email) throws SQLException {
        Cliente cliente = null;

        String query = "SELECT * FROM clientes WHERE email = ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setString(1, email);

        ResultSet data = stm.executeQuery();

        if (data.next()) {
            cliente = new Cliente.ClienteBuilder().idCliente(data.getInt("id_cliente")).nome(data.getString("nome")).senha(data.getString("senha")).email(data.getString("email")).build();
        }

        return cliente;
    }
}
