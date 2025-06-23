package models.repository;

import models.Database;
import models.repository.sql.SQLOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Repository<T> {
    protected static final Connection connection = Database.getConnection();

    public abstract void create(T t);
    public abstract void update(T t);
    public abstract void delete(Integer id);
    public abstract List<T> list();

    protected int executeUpdate(String sql, Consumer<PreparedStatement> consumer) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            consumer.accept(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected <R> List<R> executeQuery(String sql, Function<ResultSet, R> function) {
        List<R> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                results.add(function.apply(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected void inTransaction(SQLOperation op) {
        try {
            connection.setAutoCommit(false);
            op.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) { }
            throw new RuntimeException("Error en transacción", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) { }
        }
    }
}

