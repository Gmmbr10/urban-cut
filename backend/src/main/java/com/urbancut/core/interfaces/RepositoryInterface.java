package com.urbancut.core.interfaces;

import com.urbancut.core.Model;

import java.sql.SQLException;

public interface RepositoryInterface<T extends Model> {
    boolean save(T model) throws SQLException;

    T searchById(int id) throws SQLException;

    boolean update(T model) throws SQLException;

    boolean delete(int id) throws SQLException;
}
