package dev.vnco.menu.api;

import dev.vnco.menu.api.listener.MenuListener;
import dev.vnco.menu.examples.ExampleJavaPlugin;
import dev.vnco.menu.utils.Tasks;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class MenuManager {

    private final ExampleJavaPlugin plugin;
    private final Map<UUID, Menu> menuCache;
    private BukkitTask task;

    public MenuManager(ExampleJavaPlugin plugin){
        this.plugin = plugin;
        this.menuCache = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(new MenuListener(this), plugin);
    }

    public void addPlayerToMenu(Player player, Menu menu){
        if (this.contains(player)){
            return;
        }

        menu.addPlayer(player);

        this.menuCache.put(player.getUniqueId(), menu);

        if (menu.isAutoUpdate()){
            this.task = Tasks.asyncTimer(() -> menu.openMenu(player), 0L, 20L);
        }
    }

    public void removePlayerFromMenu(Player player, Menu menu){
        if (!this.contains(player)){
            return;
        }

        menu.removePlayer(player);

        this.menuCache.remove(player.getUniqueId());

        if (menu.isAutoUpdate()){
            if (this.task != null){
                this.task.cancel();
            }
        }
    }

    public Menu getMenuByPlayer(Player player){
        for (Menu menu : this.menuCache.values()){
            if (menu.contains(player)){
                return menu;
            }
        }
        return null;
    }

    public boolean contains(Player player){
        return this.menuCache.containsKey(player.getUniqueId());
    }

}
