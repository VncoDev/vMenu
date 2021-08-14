package dev.vnco.menu.api;

import dev.vnco.menu.examples.ExampleJavaPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class MenuManager {

    private final ExampleJavaPlugin plugin;
    private final Map<UUID, Menu> menuCache;

    public MenuManager(ExampleJavaPlugin plugin){
        this.plugin = plugin;
        this.menuCache = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(new MenuListener(plugin), plugin);
    }

    public void addPlayerToCache(Player player, Menu menu){
        if (this.menuCache.containsKey(player.getUniqueId())){
            return;
        }

        this.menuCache.put(player.getUniqueId(), menu);
    }

    public void removePlayerFromCache(Player player, Menu menu){
        if (!this.menuCache.containsKey(player.getUniqueId())){
            return;
        }

        this.menuCache.remove(player.getUniqueId(), menu);
    }

    public Menu getMenuByPlayer(Player player){
        for (Menu menu : this.menuCache.values()){
            if (player.getOpenInventory().getTopInventory().equals(menu.getInventory())){
                return menu;
            }
        }
        return null;
    }

    public boolean contains(Player player){
        return this.menuCache.containsKey(player.getUniqueId());
    }

}
