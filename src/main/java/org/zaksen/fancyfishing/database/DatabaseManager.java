package org.zaksen.fancyfishing.database;

import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zaksen.fancyfishing.FancyFishing;
import org.zaksen.fancyfishing.data.FishingPlayer;

import java.sql.*;

public class DatabaseManager {
    public static final DatabaseManager instance = new DatabaseManager();
    private final Logger logger;
    private Connection connection;
    private Statement statement;

    private DatabaseManager() {
        logger = LoggerFactory.getLogger(FancyFishing.class);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:player-fishing.sqlite");
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS players(uuid TEXT, fishingXp INTEGER, caughtMobs INTEGER, caughtItem INTEGER);");
        } catch (SQLException e) {
            logger.error("unstable database connection: ", e);
        }
    }

    public void addNewPlayer(Player player) {
        try {
            PreparedStatement newSt = connection.prepareStatement("INSERT INTO players(uuid, fishingXp, caughtMobs, caughtItem) VALUES(?, ?, ?, ?);");
            newSt.setString(1, player.getUniqueId().toString());
            newSt.setInt(2, 0);
            newSt.setInt(3, 0);
            newSt.setInt(4, 0);
            newSt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to add player to database: ", e);
        }
    }

    public void setPlayerXp(Player player, int data) {
        try {
            PreparedStatement newSt = connection.prepareStatement("UPDATE players SET fishingXp = ? WHERE uuid = ?;");
            newSt.setInt(1, data);
            newSt.setString(2, player.getUniqueId().toString());
            newSt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to change player data in database: ", e);
        }
    }

    public void setPlayerData(Player player, String name, int value) {
        try {
            PreparedStatement newSt = connection.prepareStatement("UPDATE players SET " + name + " = ? WHERE uuid = ?;");
            newSt.setInt(1, value);
            newSt.setString(2, player.getUniqueId().toString());
            newSt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to change player data in database: ", e);
        }
    }

    public FishingPlayer getPlayerData(Player player) {
        FishingPlayer result = null;
        ResultSet messageSet;
        try {
            messageSet = statement.executeQuery("SELECT * FROM players;");
            while (messageSet.next()) {
                String uuid = messageSet.getString("uuid");
                if(uuid.equals(player.getUniqueId().toString())) {
                    int fishingXp = messageSet.getInt("fishingXp");
                    int catchMobs = messageSet.getInt("caughtMobs");
                    int catchItems = messageSet.getInt("caughtItem");
                    result = new FishingPlayer(player, fishingXp, catchMobs, catchItems);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed to get player from database: ", e);
        }
        return result;
    }
}