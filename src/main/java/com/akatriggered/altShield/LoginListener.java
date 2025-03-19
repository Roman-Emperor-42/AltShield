package com.akatriggered.altShield;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {
    private final AltShield plugin = AltShield.getInstance();
    private final DatabaseManager database = plugin.getDatabase();
    private final FileConfiguration config = plugin.getPluginConfig();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String username = player.getName().toLowerCase();
        String ip = event.getAddress().getHostAddress();

        // Check if player has bypass permission
        if (player.hasPermission(config.getString("bypass_permission", "altshield.bypass"))) {
            return;
        }

        // Config settings
        int maxAccounts = config.getInt("max_accounts", 2);
        int maxIPs = config.getInt("max_ips", 3);
        String kickMessageAccounts = ChatColor.translateAlternateColorCodes('&', config.getString("kick_message_accounts", "&cYou have reached the maximum accounts allowed on this IP!"));
        String kickMessageIPs = ChatColor.translateAlternateColorCodes('&', config.getString("kick_message_ips", "&cToo many accounts detected from your IP!"));

        // Count accounts per IP
        int accountCount = database.countAccountsByIP(ip);

        // Kick if limit exceeded
        if (accountCount >= maxAccounts) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessageAccounts);
            return;
        }

        // Save login data
        database.savePlayerData(uuid, username, ip);
    }
}
