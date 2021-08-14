package dev.vnco.menu.api;

import dev.vnco.menu.api.button.Button;
import dev.vnco.menu.examples.ExampleJavaPlugin;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

@AllArgsConstructor
public class MenuListener implements Listener {

    private final ExampleJavaPlugin plugin;

    @EventHandler public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ClickType click = event.getClick();

        int slot = event.getSlot();

        Menu menu = this.plugin.getMenuManager().getMenuByPlayer(player);

        if (menu != null){

            if (slot != event.getRawSlot()) {
                return;
            }

            if (this.plugin.getMenuManager().contains(player)){
                event.setCancelled(true);
            }

            for (Button button : menu.getButtons()) {
                if (slot == button.getSlot()){
                    event.setCancelled(button.isCancelClick());
                    button.onClick(player, click);
                }
            }
        }
    }

    @EventHandler public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        Menu menu = this.plugin.getMenuManager().getMenuByPlayer(player);

        if (menu != null){
            menu.onClose(player);
        }
    }

}
