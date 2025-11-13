package core;

import java.sql.Connection;

public abstract class Repository {
    protected Connection database = new Database().getConexao();
}
