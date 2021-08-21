package dev.vnco.menu.api.listener;

import dev.vnco.menu.api.Menu;
import dev.vnco.menu.api.MenuManager;
import dev.vnco.menu.api.button.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

@AllArgsConstructor
public class MenuListener implements Listener {

    private final MenuManager menuManager;

    @EventHandler public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ClickType click = event.getClick();

        int slot = event.getSlot();

        Menu menu = this.menuManager.getMenuByUUID(player.getUniqueId());

        if (menu != null){

            if (this.menuManager.contains(player)) {
                event.setCancelled(true);
            }

            for (Button button : menu.getButtons(player)){
                if (slot == button.getSlot()){
                    event.setCancelled(button.isCancelClick());
                    button.onClick(player, click);
                }
            }
        }
    }

    @EventHandler public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        Menu menu = this.menuManager.getMenuByUUID(player.getUniqueId());

        if (menu != null){
            menu.onClose(player);
        }
    }

}
