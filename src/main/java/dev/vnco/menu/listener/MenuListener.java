package dev.vnco.menu.listener;

import dev.vnco.menu.Menu;
import dev.vnco.menu.MenuHandler;
import dev.vnco.menu.button.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.server.PluginDisableEvent;

import java.util.Optional;

@AllArgsConstructor
public class MenuListener implements Listener {

    private final MenuHandler menuHandler;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Optional<Menu> optionalMenu = menuHandler.findMenu(player.getUniqueId());

        if (!optionalMenu.isPresent()) {
            return;
        }

        for (Button button : optionalMenu.get().getButtons(player)) {
            if (event.getSlot() == button.getSlot()) {
                button.onClick(event);
            }
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        menuHandler.findMenu(player.getUniqueId()).ifPresent(menu -> menu.onClose(player));
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        menuHandler.getMenuRunnable().cancel();
        menuHandler.getMenuMap().clear();

        HandlerList.unregisterAll(this);
    }
}
