package models.repository.impl;

import entities.BusinessUser;
import entities.PersonalUser;
import entities.Role;
import models.repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

public class BusinessUserRepository extends Repository<BusinessUser> {

    @Override
    public void create(BusinessUser user) {
        String sqlUser = "INSERT INTO usuarios (dni, password, nombre, rol) VALUES (?, ?, ?, ?)";
        String sqlBusiness = "INSERT INTO usuario_empresa  (dni, razon_social, sector, descripcion) VALUES (?, ?, ?, ?)";

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

            executeUpdate(sqlBusiness, ps -> {
                try {
                    ps.setString(1, user.getDni());
                    ps.setString(2, user.getRazonSocial());
                    ps.setString(3, user.getSector());
                    ps.setString(4, user.getDescripcion());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario empresa.", e);
                }
            });
        });
    }

    @Override
    public void update(BusinessUser user) {
        String sqlUser = "UPDATE usuarios SET password = ?, nombre = ?, rol = ? WHERE dni = ?";
        String sqlBusiness = "UPDATE  SET razon_social = ?, sector = ?, descripcion = ? WHERE dni = ?";

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

            executeUpdate(sqlBusiness, ps -> {
                try {
                    ps.setString(1, user.getRazonSocial());
                    ps.setString(2, user.getSector());
                    ps.setString(3, user.getDescripcion());
                    ps.setString(4, user.getDni());
                } catch (SQLException e) {
                    throw new RuntimeException("Error de guardado en la tabla usuario empresa.", e);
                }
            });
        });
    }

    @Override
    public void delete(Integer id) {
        String sqlBusiness = "DELETE FROM usuario_empresa WHERE dni = ?";
        String sqlUser = "DELETE FROM usuarios WHERE dni = ?";

        inTransaction(() -> {
            executeUpdate(sqlBusiness,ps -> {
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
    public List<BusinessUser> list() {
        String sql = "SELECT u.dni, u.password, u.nombre, u.rol, "
                + "e.razon_social, e.sector, e.descripcion "
                + "FROM usuarios u "
                + "JOIN usuario_empresa e ON u.dni = e.dni";

        return executeQuery(sql, rs -> {
            try {
                return BusinessUser.builder()
                        .dni(rs.getString("dni"))
                        .password(rs.getString("password"))
                        .nombre(rs.getString("nombre"))
                        .rol(Role.valueOf(rs.getString("rol")))
                        .razonSocial(rs.getString("razon_social"))
                        .sector(rs.getString("sector"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
            } catch (SQLException e) {
                throw new RuntimeException("Error mapeando BusinessUser", e);
            }
        });
    }
}
