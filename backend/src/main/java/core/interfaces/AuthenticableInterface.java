package core.interfaces;

import core.Model;

import java.sql.SQLException;

public interface AuthenticableInterface<T extends Model> {
    T searchByEmail(String email) throws SQLException;
}
