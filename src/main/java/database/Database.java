package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tomo.
 */
public class Database {

    private final Connection connection;

    public Database(Connection connection) {

        this.connection = connection;
    }

    public void insertTraining(String name) throws DatabaseException {

        PreparedStatement statement = getPreparedStatement("INSERT INTO trainings(itemName) VALUES (?)");
        try {
            statement.setString(1, name);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert training " + e.getMessage());
        }
    }

    public void dropTable(String name) throws DatabaseException {

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("DROP TABLE IF EXISTS " + name);
            statement.execute();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to drop " + name + "table: " + e.getMessage());
        }
    }

    public void createTable() throws DatabaseException {

        Statement stmt;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create statement: " + e.getMessage());
        }

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS trainings( id INT AUTO_INCREMENT, itemName varchar(128), state varchar(50), primary key (id));");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create trainings table: " + e.getMessage());
        }
    }


    private PreparedStatement getPreparedStatement(String query) throws DatabaseException {

        try {
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create statement " + e.getMessage());
        }
    }
}
