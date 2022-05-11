package dev.vnco.menu;

import dev.vnco.menu.listener.MenuListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public class MenuHandler {

    @Getter
    private static MenuHandler instance;

    private final Map<UUID, Menu> menuMap;
    private final MenuRunnable menuRunnable;

    public MenuHandler(JavaPlugin plugin){
        instance = this;
        menuMap = new HashMap<>();

        menuRunnable = new MenuRunnable(plugin);

        Bukkit.getPluginManager().registerEvents(new MenuListener(this), plugin);
    }

    /**
     * Add a UUID to the map menu
     *
     * @param uuid - UUID | Key
     * @param menu - Menu | Value
     */

    public void registerMenu(UUID uuid, Menu menu){
        menuMap.put(uuid, menu);
    }

    /**
     * Remove a UUID from the map
     *
     * @param uuid - UUID to remove
     */

    public void unregisterMenu(UUID uuid){
        menuMap.remove(uuid);
    }

    /**
     * Get the menu of a UUID on the map
     *
     * @param uuid - UUID to get the menu
     */

    public Optional<Menu> findMenu(UUID uuid){
        return Optional.ofNullable(menuMap.get(uuid));
    }
}
