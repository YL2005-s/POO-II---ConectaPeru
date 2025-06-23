package models.repository.sql;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLOperation {
    void execute() throws SQLException;
}
