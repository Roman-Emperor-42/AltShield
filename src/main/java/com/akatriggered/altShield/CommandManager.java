package com.akatriggered.altShield;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    private final AltShield plugin = AltShield.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(ChatColor.RED + "Usage: /altshield reload");
            return true;
        }

        // Reload config
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "AltShield configuration reloaded!");
        return true;
    }
}
