package com.urbancut.core.interfaces;

import com.urbancut.core.Model;

import java.sql.SQLException;

public interface RepositoryInterface<T extends Model> {
    void save(T model) throws SQLException;

    T searchById(int id) throws SQLException;

    void update(T model) throws SQLException;

    void delete(int id) throws SQLException;
}
