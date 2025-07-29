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
        int maxAccounts = config.getInt("max_accounts");
        int maxIPs = config.getInt("max_ips");
        boolean countOnlineOnly = config.getBoolean("check_online_only");
        String kickMessageAccounts = ChatColor.translateAlternateColorCodes('&', config.getString("kick_message_accounts", "&cYou have reached the maximum accounts allowed on this IP!"));
        String kickMessageIPs = ChatColor.translateAlternateColorCodes('&', config.getString("kick_message_ips", "&cToo many accounts detected from your IP!"));

        plugin.getLogger().info("count_online_only in config is: " + countOnlineOnly);


        int accountCount = 0;
        if (countOnlineOnly) {
            // Count only currently online accounts with same IP
            for (Player online : plugin.getServer().getOnlinePlayers()) {
                if (online.getAddress() != null && online.getAddress().getAddress().getHostAddress().equals(ip)) {
                    accountCount++;
                }
            }
            if (accountCount >= maxAccounts) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessageAccounts);
                return;
            }
            database.savePlayerData(uuid, username, ip);

        } else {
            accountCount = database.countAccountsByIP(ip);
            boolean alreadyJoined = database.playerExists(uuid, ip);

            if (!alreadyJoined && accountCount >= maxAccounts) {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessageAccounts);
                return;
            }
            if (!alreadyJoined) {
                database.savePlayerData(uuid, username, ip);
            }
        }
    }
}
