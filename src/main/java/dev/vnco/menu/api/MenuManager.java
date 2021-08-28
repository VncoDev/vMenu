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

    /**
     * Add a player to the map menu
     *
     * @param player - Player | Key
     * @param menu - Menu | Value
     */

    public void addPlayerToMenu(Player player, Menu menu){
        if (this.contains(player)){
            return;
        }

        this.menuMap.put(player.getUniqueId(), menu);
    }

    /**
     * Remove a player from the map
     *
     * @param player - Player to remove
     */

    public void removePlayerFromMenu(Player player){
        if (!this.contains(player)){
            return;
        }

        this.menuMap.remove(player.getUniqueId());
    }

    /**
     * Get the menu of a UUID on the map
     *
     * @param uuid - UUID to get the menu
     */

    public Menu getMenuByUUID(UUID uuid){
        for (Map.Entry<UUID, Menu> entry : this.menuMap.entrySet()){
            Menu menu = entry.getValue();

            if (Bukkit.getPlayer(uuid).getOpenInventory().getTopInventory().equals(menu.getInventory())){
                return menu;
            }
        }
        return null;
    }

    /**
     * Checking to see if a player is on the map
     *
     * @param player - Player to check
     */

    public boolean contains(Player player){
        return this.menuMap.containsKey(player.getUniqueId());
    }

}
