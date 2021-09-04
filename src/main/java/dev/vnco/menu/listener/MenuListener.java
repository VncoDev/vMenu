package dev.vnco.menu.listener;

import dev.vnco.menu.Menu;
import dev.vnco.menu.MenuManager;
import dev.vnco.menu.button.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.function.Consumer;

@AllArgsConstructor
public class MenuListener implements Listener {

    private final MenuManager menuManager;

    @EventHandler public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Menu menu = this.menuManager.getMenuByUUID(player.getUniqueId());
        Consumer<Button> buttonConsumer = b -> b.onClick(event);

        if (menu != null){

            if (this.menuManager.contains(player)) {
                event.setCancelled(true);
            }

            for (Button button : menu.getButtons(player)){
                if (event.getSlot() == button.getSlot()) {
                    buttonConsumer.accept(button);
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
