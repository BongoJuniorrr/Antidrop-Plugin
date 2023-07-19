package me.hirobi;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.Console;

public final class antidrop extends JavaPlugin {
    public FileConfiguration config;
    private static antidrop INSTANCE;
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        config = getConfig();
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new PlayerDrop(), this);
    }

    @Override
    public void onDisable() {
    }

    public static antidrop get() {
        return INSTANCE;
    }
    public FileConfiguration getConfigMethods()
    {
        return config;
    }

}
