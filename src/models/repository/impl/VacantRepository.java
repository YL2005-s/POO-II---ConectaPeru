package models.repository.impl;

import entities.Vacant;
import models.repository.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class VacantRepository extends Repository<Vacant> {

    @Override
    public void create(Vacant v) {
        String sql = """
            INSERT INTO vacantes
            (titulo, descripcion, salario, ubicacion, modalidad, created_at)
            VALUES (?, ?, ?, ?, ?, ?)""";

        executeUpdate(sql, ps -> {
            try {
                ps.setString(1, v.getTitulo());
                ps.setString(2, v.getDescripcion());
                ps.setDouble(3, v.getSalario());
                ps.setString(4, v.getUbicacion());
                ps.setString(5, v.getRequisitos());
                ps.setTimestamp(6, Timestamp.valueOf(v.getFechas()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void update(Vacant v) {
        String sql = """
            UPDATE vacantes
            SET titulo = ?, descripcion = ?, salario = ?, ubicacion = ?, modalidad = ?, updated_at = ?
            WHERE id = ?""";

        executeUpdate(sql, ps -> {
            try {
                ps.setString(1, v.getTitulo());
                ps.setString(2, v.getDescripcion());
                ps.setDouble(3, v.getSalario());
                ps.setString(4, v.getUbicacion());
                ps.setString(5, v.getRequisitos());
                ps.setTimestamp(6, Timestamp.valueOf(v.getFechas()));
                ps.setLong(7, v.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM vacantes WHERE id = ?";

        executeUpdate(sql, ps -> {
            try {
                ps.setLong(1, id.longValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Vacant> list() {
        String sql = """
            SELECT id, titulo, descripcion, salario, ubicacion,
            modalidad AS requisitos,
            created_at AS fechas
            FROM vacantes""";

        return executeQuery(sql, rs -> {
            try {
                return new Vacant(
                        rs.getLong("id"),
                        rs.getString("titulo"),
                        rs.getDouble("salario"),
                        rs.getString("descripcion"),
                        rs.getString("ubicacion"),
                        rs.getString("requisitos"),
                        rs.getTimestamp("fechas").toLocalDateTime().toString()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
