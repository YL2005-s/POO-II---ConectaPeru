package models.repository.impl;

import entities.Inscription;
import models.repository.Repository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class InscriptionRepository extends Repository<Inscription> {

    @Override
    public void create(Inscription insc) {
        String sql = """
            INSERT INTO inscripciones
              (candidato_dni, vacante_id, estado)
            VALUES (?, ?, ?)""";

        executeUpdate(sql, ps -> {
            try {
                ps.setString(1, insc.getCandidatoDni());
                ps.setLong(2,   insc.getVacanteId());
                ps.setString(3, insc.getEstado());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void update(Inscription insc) {
        String sql = """
            UPDATE inscripciones
               SET estado = ?, fecha_inscripcion = ?
             WHERE candidato_dni = ?
               AND vacante_id    = ?""";

        executeUpdate(sql, ps -> {
            try {
                ps.setString(1, insc.getEstado());
                ps.setTimestamp(2, Timestamp.valueOf(insc.getFechaInscripcion()));
                ps.setString(3, insc.getCandidatoDni());
                ps.setLong(4,    insc.getVacanteId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM inscripciones WHERE candidato_dni = ?";

        executeUpdate(sql, ps -> {
            try {
                ps.setString(1, id.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<Inscription> list() {
        String sql = """
            SELECT candidato_dni, vacante_id, estado, fecha_inscripcion
              FROM inscripciones""";

        return executeQuery(sql, rs -> {
            try {
                return new Inscription(
                        rs.getString("candidato_dni"),
                        rs.getLong("vacante_id"),
                        rs.getString("estado"),
                        rs.getTimestamp("fecha_inscripcion").toLocalDateTime().toString()
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
