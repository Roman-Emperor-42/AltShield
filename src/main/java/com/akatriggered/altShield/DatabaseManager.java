package com.akatriggered.altShield;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:plugins/AltShield/altshield.db");
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS player_data ("
                + "uuid TEXT PRIMARY KEY, "
                + "username TEXT, "
                + "ip TEXT"
                + ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerData(String uuid, String username, String ip) {
        String query = "INSERT OR REPLACE INTO player_data (uuid, username, ip) VALUES (?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, uuid);
            stmt.setString(2, username);
            stmt.setString(3, ip);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countAccountsByIP(String ip) {
        String query = "SELECT COUNT(*) FROM player_data WHERE ip = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, ip);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(String uuid, String ip) {
        String query = "SELECT 1 FROM player_data WHERE uuid = ? AND ip = ? LIMIT 1;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, uuid);
            stmt.setString(2, ip);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
