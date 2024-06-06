package org.shc0218.minecraft.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.shc0218.minecraft.User.Update;

public class MinecraftEventListener implements Listener {
    private static Update update = new Update();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        update.PlayerJoinRequest(player);
        update.SetUserDataUpdate(player);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        update.DeleteUserDataUpdate(player);
        update.PlayerQuitRequest(player);
    }
}
