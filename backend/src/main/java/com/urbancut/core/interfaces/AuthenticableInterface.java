package com.urbancut.core.interfaces;

import com.urbancut.core.Model;

import java.sql.SQLException;

public interface AuthenticableInterface<T extends Model> {
    T searchByEmail(String email) throws SQLException;
}
