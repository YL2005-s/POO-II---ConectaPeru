package models.repository.impl;

import entities.PersonalUser;
import entities.Role;
import entities.User;
import models.repository.Repository;

import java.sql.SQLException;
import java.util.List;

public class PersonalUserRepository extends Repository<PersonalUser> {

    @Override
    public void create(PersonalUser user) {
        String sqlUser = "INSERT INTO usuarios (dni, password, nombre, rol) VALUES (?, ?, ?, ?)";
        String sqlPersonal = "INSERT INTO usuario_personal (dni, cv, experiencia, estudios) VALUES (?, ?, ?, ?)";

        inTransaction(() -> {
            executeUpdate(sqlUser, ps -> {
                try {
                    ps.setString(1, user.getDni());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getNombre());
                    ps.setString(4, user.getRol().name());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario.", e);
                }
            });

            executeUpdate(sqlPersonal, ps -> {
                try {
                    ps.setString(1, user.getDni());
                    ps.setString(2, user.getCv());
                    ps.setString(3, user.getExperiencia());
                    ps.setString(4, user.getEstudios());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario personal.", e);
                }
            });
        });
    }

    @Override
    public void update(PersonalUser user) {
        String sqlUser = "UPDATE usuarios SET password = ?, nombre = ?, rol = ? WHERE dni = ?";
        String sqlPersonal = "UPDATE usuario_personal SET cv = ?, experiencia = ?, estudios = ? WHERE dni = ?";

        inTransaction(() -> {
            executeUpdate(sqlUser, ps -> {
                try {
                    ps.setString(1, user.getPassword());
                    ps.setString(2, user.getNombre());
                    ps.setString(3, user.getRol().name());
                    ps.setString(4, user.getDni());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario.", e);
                }
            });

            executeUpdate(sqlPersonal, ps -> {
                try {
                    ps.setString(1, user.getCv());
                    ps.setString(2, user.getExperiencia());
                    ps.setString(3, user.getEstudios());
                    ps.setString(4, user.getDni());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario.", e);
                }
            });
        });
    }

    @Override
    public void delete(Integer id) {
        String sqlPersonal = "DELETE FROM usuario_personal WHERE dni = ?";
        String sqlUser = "DELETE FROM usuarios WHERE dni = ?";

        inTransaction(() -> {
            executeUpdate(sqlPersonal,ps -> {
                try {
                    ps.setString(1, id.toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            executeUpdate(sqlUser,ps -> {
                try {
                    ps.setString(1, id.toString());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Override
    public List<PersonalUser> list() {
        String sql = "SELECT u.dni, u.password, u.nombre, u.rol, "
                + "p.cv, p.experiencia, p.estudios "
                + "FROM usuarios u "
                + "JOIN usuario_personal p ON u.dni = p.dni";

        return executeQuery(sql, rs -> {
            try {
                return PersonalUser.builder()
                        .dni(rs.getString("dni"))
                        .password(rs.getString("password"))
                        .nombre(rs.getString("nombre"))
                        .rol(Role.valueOf(rs.getString("rol")))
                        .cv(rs.getString("cv"))
                        .experiencia(rs.getString("experiencia"))
                        .estudios(rs.getString("estudios"))
                        .build();
            } catch (SQLException e) {
                throw new RuntimeException("Error mapeando PersonalUser", e);
            }
        });
    }

}
