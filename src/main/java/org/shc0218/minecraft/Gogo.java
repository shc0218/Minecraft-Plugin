package org.shc0218.minecraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.shc0218.minecraft.event.MinecraftEventListener;
import org.shc0218.minecraft.event.MinecraftEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Gogo extends JavaPlugin {
    FileConfiguration config = getConfig();
    private BukkitScheduler BukkitScheduler;

    @Override
    public void onEnable() {
        getServer().getLogger().info("Plugin enabled");
        getServer().getPluginManager().registerEvents(new MinecraftEventListener(), this);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("Plugin disable");
    }
}
