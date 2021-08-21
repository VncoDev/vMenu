package dev.vnco.menu.api;

import dev.vnco.menu.api.listener.MenuListener;
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
    private final Map<UUID, Menu> menuMap;

    public MenuManager(ExampleJavaPlugin plugin){
        this.plugin = plugin;
        this.menuMap = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(new MenuListener(this), plugin);
    }

    public void addPlayerToMenu(Player player, Menu menu){
        if (this.contains(player)){
            return;
        }

        this.menuMap.put(player.getUniqueId(), menu);
    }

    public void removePlayerFromMenu(Player player){
        if (!this.contains(player)){
            return;
        }

        this.menuMap.remove(player.getUniqueId());
    }

    public Menu getMenuByPlayer(Player player){
        for (Menu menu : this.menuMap.values()){
            if (player.getOpenInventory().getTopInventory().equals(menu.getInventory())){
                return menu;
            }
        }
        return null;
    }

    public boolean contains(Player player){
        return this.menuMap.containsKey(player.getUniqueId());
    }

}
