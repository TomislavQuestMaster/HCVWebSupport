package hcv.database;

import hcv.model.Coach;
import hcv.model.RightsLevel;

import java.sql.*;

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

    public void insertCoach(Coach coach) throws DatabaseException {

        PreparedStatement statement = getPreparedStatement("INSERT INTO coaches(username, password, rightsLevel) VALUES (?, ?, ?)");
        try {
            statement.setString(1, coach.getUsername());
            statement.setString(2, coach.getPassword());
            statement.setString(3, coach.getRightsLevel().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert coach: " + e.getMessage());
        }
    }

    public boolean authenticateCoach(Coach coach) throws DatabaseException {

        PreparedStatement statement = getPreparedStatement("SELECT * FROM coaches WHERE username = ? AND password = ?");
        try {
            statement.setString(1, coach.getUsername());
            statement.setString(2, coach.getPassword());
            ResultSet result = statement.executeQuery();

            if(!result.next()){
                return false;
            }
            coach.setRightsLevel(RightsLevel.valueOf(result.getString("rightsLevel")));
            statement.close();
            return true;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch coach: " + e.getMessage());
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

    public void createTrainingsTable() throws DatabaseException {

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

    public void createCoachesTable() throws DatabaseException {

        Statement stmt;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create statement: " + e.getMessage());
        }

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS coaches( id INT AUTO_INCREMENT, username varchar(128), password varchar(128), rightsLevel varchar(128), primary key (id));");
        } catch (SQLException e) {
            throw new DatabaseException("Failed to create coaches table: " + e.getMessage());
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
