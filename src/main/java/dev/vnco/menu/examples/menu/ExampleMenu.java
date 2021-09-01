package dev.vnco.menu.examples.menu;

import dev.vnco.menu.api.Menu;
import dev.vnco.menu.api.button.Button;
import dev.vnco.menu.api.type.FillType;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

/**
 * Example Menu
 */

public class ExampleMenu extends Menu {

    public ExampleMenu() {
        super("&c&lExample Menu", 3);

        this.setFillEnabled(true);
        this.setFillType(FillType.BORDERS);
        this.setFillItemStack(this.getFillingItemStack());
    }

    @Override public void onOpen(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 5.0f, 5.0f);
    }

    @Override public void onClose(Player player) {
        player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 5.0f, 5.0f);
    }

    @Override public Set<Button> getButtons(Player player) {
        Set<Button> buttons = new HashSet<>();

        buttons.add(new ExampleButton());

        return buttons;
    }

    private ItemStack getFillingItemStack(){
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(" ");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
