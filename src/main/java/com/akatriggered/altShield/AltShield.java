package com.akatriggered.altShield;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class AltShield extends JavaPlugin {
    private static AltShield instance;
    private DatabaseManager database;
    private Logger logger;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();

        // Load config
        saveDefaultConfig();
        config = getConfig();

        // Initialize database
        database = new DatabaseManager();

        // Register commands and events
        getCommand("altshield").setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new LoginListener(), this);

        logger.info("AltShield enabled successfully.");
    }

    @Override
    public void onDisable() {
        database.closeConnection();
        logger.info("AltShield disabled and database connection closed.");
    }

    public static AltShield getInstance() {
        return instance;
    }

    public DatabaseManager getDatabase() {
        return database;
    }

    public FileConfiguration getPluginConfig() {
        return config;
    }
}
