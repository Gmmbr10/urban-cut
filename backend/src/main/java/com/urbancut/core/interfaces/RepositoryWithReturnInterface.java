package com.urbancut.core.interfaces;

import com.urbancut.core.Model;

import java.sql.SQLException;

public interface RepositoryWithReturnInterface<T extends Model> extends RepositoryInterface<T> {
    @Override
    default void save(T model) throws SQLException {
        return;
    }

    int saveWithReturn(T model) throws SQLException;

    @Override
    T searchById(int id) throws SQLException;

    @Override
    void update(T model) throws SQLException;

    @Override
    void delete(int id) throws SQLException;
}
