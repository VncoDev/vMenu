package dev.vnco.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class MenuRunnable extends BukkitRunnable {

    public MenuRunnable(JavaPlugin plugin) {
        runTaskTimer(plugin, 1L, 1L);
    }

    @Override
    public void run() {
        for (Map.Entry<UUID, Menu> entry : MenuHandler.getInstance().getMenuMap().entrySet()) {
            Player player = Bukkit.getPlayer(entry.getKey());

            if (player != null){
                entry.getValue().openMenu(player);
            }
        }
    }
}
