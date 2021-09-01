package dev.vnco.menu.examples.menu;

import com.google.common.base.Strings;
import dev.vnco.menu.api.button.Button;
import dev.vnco.menu.utils.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Example Button
 */

public class ExampleButton extends Button {

    public ExampleButton() {
        super(14);
    }

    @Override public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ClickType clickType = event.getClick();

        if (clickType.isLeftClick()){
            Color.toPlayer(player, "Ping");
        } else {
            Color.toPlayer(player, "Pong");
        }

        player.closeInventory();
    }

    @Override public ItemStack getButtonItem() {
        ItemStack itemStack = new ItemStack(Material.GLOWSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(Color.translate("&c&lExample Button"));

        List<String> list = new ArrayList<>();

        list.add(Strings.repeat("&7&m-", 15));
        list.add("&f&oClick here...");
        list.add(Strings.repeat("&7&m-", 15));

        itemMeta.setLore(Color.translateFromArrayList(list));

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
