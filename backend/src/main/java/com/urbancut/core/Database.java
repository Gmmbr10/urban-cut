package com.urbancut.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String banco = "urban_cut";
    private String server = "db:3306";
    private String caminho = "jdbc:mysql://" + server + "/" + banco;
    private String usuario = "root";
    private String senha = "root";
    private String driver = "com.mysql.jdbc.Driver";

    public Connection getConexao() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro: Não foi possível conectar ao banco de dados.\n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro: Classe não encontrada.\n" + e.getMessage());
        }
    }
}
